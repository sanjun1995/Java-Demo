package com.sanjun.project.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.support.ResourcePropertySource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DynamicDataSourceC3p0 implements ApplicationContextAware,ApplicationListener {

    private ApplicationContext app;

    @Override
    public void setApplicationContext(ApplicationContext app)
            throws BeansException {
        this.app = app;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        //如果是容器刷新事件
        if(event instanceof ContextRefreshedEvent ){
            try {
                regDynamicBean();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(event.getClass().getSimpleName()+" 事件已发生！");
        }
    }

    private void regDynamicBean() throws IOException{
        // 解析属性文件，得到数据源Map
        Map<String, DataSourceInfo> mapCustom = parsePropertiesFile("application.properties");
        // 把数据源bean注册到容器中
        addSourceBeanToApp(mapCustom);

    }
    /**
     * 功能说明：根据DataSource创建bean并注册到容器中
     * @param mapCustom
     */
    private void addSourceBeanToApp(Map<String, DataSourceInfo> mapCustom) {
        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app.getAutowireCapableBeanFactory();
        BeanDefinition beanDefinition;
        Iterator<String> iter = mapCustom.keySet().iterator();
        while(iter.hasNext()){
            String beanKey = iter.next();
            // 得到Bean定义，并添加到容器中
            beanDefinition = new ChildBeanDefinition("dataSource");
            // 注意：必须先注册到容器中，再得到Bean进行修改，否则数据源属性不能有效修改
            acf.registerBeanDefinition(beanKey, beanDefinition);
            // 再得到数据源Bean定义，并修改连接相关的属性
            ComboPooledDataSource cpds = (ComboPooledDataSource)app.getBean( beanKey);;

            cpds.setJdbcUrl(mapCustom.get(beanKey).connUrl);
            cpds.setUser(mapCustom.get(beanKey).userName);
            cpds.setPassword(mapCustom.get(beanKey).password);
        }
    }
    /**
     * 功能说明：解析属性文件，得到数据源Map
     * @return
     * @throws IOException
     */
    private Map<String, DataSourceInfo> parsePropertiesFile(String fileName) throws IOException {
        // 属性文件
        ResourcePropertySource props =  new ResourcePropertySource(fileName);

        Matcher matcher;
        Pattern pattern = Pattern.compile("^link\\.(eagle2\\.\\w+)\\.jdbc\\.(jdbcUrl|user|password)$");

        Map<String, DataSourceInfo> mapDataSource = new HashMap<String,DataSourceInfo>();
        // 根据配置文件解析数据源
        for(String keyProp : props.getPropertyNames())
        {
            matcher = pattern.matcher(keyProp);
            if(matcher.find()){
                String dsName = matcher.group(1);
                String dsPropName = matcher.group(2);
                DataSourceInfo dsi;

                if(mapDataSource.containsKey(dsName)){
                    dsi = mapDataSource.get(dsName);
                }
                else{
                    dsi = new DataSourceInfo();
                }
                // 根据属性名给数据源属性赋值
                if("jdbcUrl".equals(dsPropName)){
                    dsi.connUrl = (String)props.getProperty(keyProp);
                }else if("user".equals(dsPropName)){
                    dsi.userName = (String)props.getProperty(keyProp);
                }else if("password".equals(dsPropName)){
                    dsi.password = (String)props.getProperty(keyProp);
                }
                mapDataSource.put(dsName, dsi);
            }
        }
        return mapDataSource;
    }
}