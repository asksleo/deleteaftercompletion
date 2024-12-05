package pageobject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.model.Field;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.w3c.dom.*;

public class SlackIntegration {
private static String SlackWebHook = "YOUR_WEBHOOK_URL";
private static String channelName = "YOUR_SLACK_CHANNEL_NAME";
private static String botUserOAuthAccessToken = " OAuth_TOKEN"; 	
@Test
public void TestStatusToSlack(String message) throws Exception { 

try {
//Make sure testng.xml location should be correct 
String path = System.getProperty("user.dir")+"/test-output/testng-results.xml";
File testNgResultXmlFile = new File(path);

//Get Document Builder
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = null;
try {
 builder = factory.newDocumentBuilder();
 } 
catch (ParserConfigurationException e) {
e.printStackTrace();
 }
 
//Build Document
Document document = null;
try {
 document = builder.parse(testNgResultXmlFile);
 } 
catch (SAXException e) {
e.printStackTrace();
 } 

//Normalize the XML Structure;
document.getDocumentElement().normalize();

//Fetch TestNG Results from XML; 
String totaltest = document.getDocumentElement().getAttribute("total");
String passed = document.getDocumentElement().getAttribute("passed");
String failed = document.getDocumentElement().getAttribute("failed");
String skipped = document.getDocumentElement().getAttribute("skipped");

StringBuilder messageBuider = new StringBuilder(); 
messageBuider.append(methodname+"========================"+"\n\n"+"Total Number of Test Cases: " + totaltest + "\n\n"+"Passed Test Cases: "+passed+"\n\n"+"Failed Test Cases: "+failed+"\n\n"+"Skipped Test Cases: "+skipped);
WebhookResponse webhookResponse = Slack.getInstance().send(SlackWebHook,payload);
webhookResponse.getMessage();
} 
catch (IOException e) {
System.out.println("Unexpected Error! WebHook:" + SlackWebHook); 
}

@Test
public void ReportToSlack(@Optional("testReportPath") String testReportPath) {
String url = "https://slack.com/api/files.upload?token="+botUserOAuthAccessToken +"&channels="+channelName+" ";
try 
{
HttpClient httpclient = HttpClientBuilder.create().build();
HttpPost httppost = new HttpPost(url);
MultipartEntityBuilder builder = MultipartEntityBuilder.create();
builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
FileBody fileBody = new FileBody(new File(testReportPath));
builder.addPart("file", fileBody);
httppost.setEntity(builder.build());
HttpResponse response = null;
response=httpclient.execute(httppost);
HttpEntity result = response.getEntity();
System.out.println(result.toString());  
} 
catch (Exception e) {
 // TODO: handle exception
 e.printStackTrace();
 }
}