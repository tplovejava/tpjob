tpjob

springcloud搭建项目  
项目架构：  
* tbjob-api：对外部接口  
* tpjob-bussiness：业务服务  
    + tpjob-baseinfo：基础服务
    + tpjob-security：权限服务  
    + tojob-util：公共工具包  
* tpjob-components：组件服务  
    + tpjob-base-config：配置文件存放目录  
    + tpjob-config-center：配置中心服务  
    + tpjob-gateway：网关  
    + tpjob-redis: 缓存  
    + tpjob-register-center: 注册中心  
    + tpjob-txlcn-center: 分布式事务服务  

#spring cloud bus消息总线 
下载rabbitmq  
官网http://www.rabbitmq.com/  
或者直接https://brew.sh/    
安装之后访问http://localhost:15672/