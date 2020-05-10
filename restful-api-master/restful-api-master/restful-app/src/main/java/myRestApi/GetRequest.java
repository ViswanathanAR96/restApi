package myRestApi;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class GetRequest {
	
	@RequestMapping()
	public JsonNode getUsers() throws JsonProcessingException, IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("./contents.json").getAbsoluteFile();
		JsonNode jsonNode = objectMapper.readTree(file);
		return (jsonNode);
	}
	
	@RequestMapping("/{userId}")
	public JsonNode getId(@PathVariable("userId") int userId)  throws IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("./contents.json").getAbsoluteFile();
		JsonNode jsonNode = objectMapper.readTree(file);
		for(JsonNode root : jsonNode) {	
			if(userId == root.path("userId").asInt()) {	
				return root;
			}
		}
		return (jsonNode);
	}
	
	@RequestMapping("/{userId}/orders")
	public JsonNode getUserOrders(@PathVariable("userId") int userId)  throws IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("./contents.json").getAbsoluteFile();
		JsonNode jsonNode = objectMapper.readTree(file);
		JsonNode ordersNode = objectMapper.createObjectNode();
		for(JsonNode root : jsonNode) {	
			if(userId == root.path("userId").asInt()) {	

				 ordersNode = root.path("orders");
			}	
			System.out.println(ordersNode);
		}
		return (ordersNode);
	}
	
	@RequestMapping("/{userId}/orders/{orderId}")
	public JsonNode getUserOrders(@PathVariable("userId") int userId,
			@PathVariable("orderId") String orderId)  throws IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("./contents.json").getAbsoluteFile();
		JsonNode jsonNode = objectMapper.readTree(file);
		JsonNode orderIdNode = objectMapper.createObjectNode();

		for(JsonNode root : jsonNode) {	
			if(userId == root.path("userId").asInt()) {	
				 JsonNode ordersNode = root.path("orders");
				 Iterator<Entry<String, JsonNode>> fields = ordersNode.fields();				
				 while(fields.hasNext()) {
					 Entry<String, JsonNode> jsonField = fields.next();					 
					 if(jsonField.getKey().equals(orderId)) {	
						 orderIdNode = jsonField.getValue();
						 break;
					 }
				 }	
			}				
		}
		return (orderIdNode);
	}
	
}
