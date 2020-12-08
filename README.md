springcloud-theLife



服务端口：
  配置服务：
    TheLife-Gateway：80
    TheLife-Eureka：6000
  业务服务：
    TheLife-Server-About：9000
    TheLife-Server-Jobsearch：9010
    
    
调用流程：
  controller → service → dao → mapper
    controller：rest层
    service：业务层
    dao：数据交互层（数据封装，加缓存等）
    mapper：数据持久化层