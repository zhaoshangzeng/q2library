package cn.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Web层工具类
 * 提供方法:1.map转bean 2.bean转bean 3.获得UUID全球唯一ID，用作表记录id
 * 需要beanUitl的jar包及其依赖jar包
 * @author zzshang
 *
 */
public class WebUtils {
	//请求参数转成bean属性值
		public static <T> T toBean(Map<String,String[]> map, Class<T> beanClass){
			//参数定义为class，可以让调用者不用new对象，直接传class类型即可
			//使用泛型，可以让调用者获得结果时，不用强转类型
			try {
				//1.创建要封装数据的bean
				T bean = beanClass.newInstance();
				
				//2.把数据整到bean中
				BeanUtils.populate(bean, map);
				
				return bean;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		//FormBean属性值 转成 UserBean属性值
		public static void copyBean(Object dest, Object src){
			try {
				BeanUtils.copyProperties(dest, src);
			} catch (Exception e){
				throw new RuntimeException(e);
			}
		}
		
		//产生用户id，产生全球唯一的id
		public static String generateID(){
			
			UUID randomUUID = UUID.randomUUID();
			String uuid = randomUUID.toString().replace("-", "").toUpperCase();
			return uuid;
		}

		//获得固定格式的当前时间
		public static String getTime() {
			
			return new SimpleDateFormat("yyy-MM-dd").format(new Date());
		}
}
