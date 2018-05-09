package redisTest;

import cn.e3mall.MyRedis.MyRedis;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class TestRedis {
    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("192.168.25.128", 6379);
        jedis.set("strkey3", "7");
        String strkey3 = jedis.get("strkey3");
        System.out.println(strkey3);
    }

    @Test
    public void testJedisPool() {
        JedisPool jedisPool = new JedisPool("192.168.25.128", 6379);
        Jedis resource = jedisPool.getResource();
        resource.set("strkey", "hello,redis");
        System.out.println(resource.get("strkey"));
        //关闭连接池与jedis
        resource.close();
        jedisPool.close();
    }

    @Test
    public void testClisterJedis() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.25.128", 7001));
        nodes.add(new HostAndPort("192.168.25.128", 7002));
        nodes.add(new HostAndPort("192.168.25.128", 7003));
        nodes.add(new HostAndPort("192.168.25.128", 7004));
        nodes.add(new HostAndPort("192.168.25.128", 7005));
        nodes.add(new HostAndPort("192.168.25.128", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("strkeyb", "1");
        System.out.println(jedisCluster.get("strkeya"));

    }

    @Test
    public void testJedisA() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext_redis.xml");
        MyRedis bean = applicationContext.getBean(MyRedis.class);
        bean.set("asdf", "asd");
        String asdf = bean.get("asdf");
        System.out.println(asdf);
    }


}
