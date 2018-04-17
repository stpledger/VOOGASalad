package engine.components;

import java.util.HashMap;
import java.util.Map;

public class Win extends Component{
	public static String KEY = "WIN";
	
	public Win(int pid, String keykey) {
		super(pid, keykey);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<>();
		return res;
	}

}
