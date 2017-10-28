package Utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLserializer implements Serializer{
	@SuppressWarnings("rawtypes")
	private Stack stack = new Stack();
	private File file;
	
	public XMLserializer(File file) {
		this.file = file;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void push(Object movieInfo) {
		stack.push(movieInfo);
	}
	
	@Override
	public Object pop() {
		return stack.pop();
	}
	
	@Override
	public void	read() throws Exception{
		ObjectInputStream is = null;
		try {
			XStream xstream = new XStream(new DomDriver());
			is = xstream.createObjectInputStream(new FileReader(file));
			Object obj = is.readObject();
			while(obj != null) {
				stack.push(obj);
				obj= is.readObject();
			}
		}
		finally {
			if(is != null)
			{
				is.close();
			}
		}
}
	
	@Override
	public void	write() throws Exception{
		ObjectOutputStream os = null;
		try {
			XStream xstream = new XStream(new DomDriver());
			os = xstream.createObjectOutputStream(new FileWriter(file));
			while(!stack.empty()) {
				os.writeObject(stack);
			}
		}
			finally {
				if(os !=null) {
					os.close();
				}
			}
		}
}
