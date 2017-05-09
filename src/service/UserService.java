/**
 * 
 */
package service;

/**
 * @author duocai
 * @date 2017年5月9日
 * @time 上午12:53:47
 */
public interface UserService {

	boolean login(String u, String p);
	boolean register(String u, String p);
}
