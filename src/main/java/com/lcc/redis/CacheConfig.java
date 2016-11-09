package com.lcc.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.lcc.redis"})
public class CacheConfig {
	/** redis的缓存服务器的地址 */
	@Value("${redis.host}")
	private String host;
	 /** redis缓存服务器端口 */
    @Value("${redis.port}")
    private Integer port;
    /** redis缓存服务器连接超时时间 */
    @Value("${redis.timeout}")
    private Integer timeout;
    
    @Bean(name="jedisPool")
    public JedisPool jedisPool(){
    	JedisPoolConfig config = new JedisPoolConfig();
    	//最大等待时间
    	config.setMaxWaitMillis(3000);
    	//最大链接数
    	config.setMaxTotal(32);
    	//如许可以最小的空闲链接数
    	config.setMinIdle(6);
    	//申请链接是否有效
    	config.setTestOnBorrow(false);
    	//用完放回池子有效性验证
    	config.setTestOnReturn(false);
    	//申请到连接时,如果空闲时间大于TimeBetweenEvictionRunsMillis时间,效验连接是否有效,建议开启,对性能有效不大
    	config.setTestWhileIdle(true);
    	//TestWhileIdle的判断依据
    	config.setTimeBetweenEvictionRunsMillis(30000);
    	return new JedisPool(config,host,timeout);
    }

}
