import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;





import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**   
 * @Title: MemcachedTest.java 
 * @author lxh  
 * @date Jan 29, 2016 9:12:26 AM  
 * 
 */

/**
 * @author lxh
 *
 */
public class MemcachedTest {
	
	public static void main(String[]args){
		String[]servers={"159.226.15.176:12345"};
		SockIOPool pool=SockIOPool.getInstance();
		pool.setServers(servers);
		pool.setFailover(true);
		pool.setInitConn(10);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setAliveCheck(true);
		pool.initialize();
		MemCachedClient memCachedClient=new MemCachedClient();
		for(int i=0;i<2;i++){
			System.out.println(i);
			boolean success=memCachedClient.set(""+i, "fefajwofjiwfjeifjiefjeifjiejfiejiejif"+i);
			//System.out.println(success);
			//String res=(String)memCachedClient.get(""+i);
			System.out.println(String.format("set(%d):%s", i,success));
			//System.out.println(String.format("get(%d):%s", i,res));
		}
		//System.out.println(memCachedClient.get("2"));
		//Map<String,Map<String,String>>slabs=memCachedClient.statsItems();
		/*for(String str:res.keySet()){
			Map<String,String>map=res.get(str);
			for(String s:map.keySet()){
				System.out.println(s+" "+map.get(s));
			}
		}*/
		/*memCachedClient.set("111", 1);
		Iterator itemItr=slabs.keySet().iterator();
		while(itemItr.hasNext()){
			String serverInfo1=itemItr.next().toString();
			Map itemNames=slabs.get(serverInfo1);
			Iterator itemNameItr=itemNames.keySet().iterator();
			while(itemNameItr.hasNext()){
				String itemName=itemNameItr.next().toString();
				System.out.println(itemName);
				String []itemAtt=itemName.split(":");
				if(itemAtt[2].startsWith("number")){
					Map chcheDump=memCachedClient.statsCacheDump(Integer.parseInt(itemAtt[1]),0);
					Iterator itr=chcheDump.keySet().iterator();
					int i=0;
					while(itr.hasNext()){
						String serverInfo2=itr.next().toString();
						Map items=(Map)chcheDump.get(serverInfo2);
						Iterator keyItr=items.keySet().iterator();
						while(keyItr.hasNext()){
							String key=keyItr.next().toString();
							String memKey=key;
							i++;
							try{
								key=URLDecoder.decode(key,"UTF-8");
								String value=(String)(items.get(memKey));
								value = value.substring(value.indexOf(";") + 2, value.indexOf(" s"));
								System.out.println("key-value: "+key+" "+value);
								System.out.println(key+"----"+memCachedClient.get(key)+"---"+value);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						
					}
				}
				
			}
		}*/
		
	}
}
