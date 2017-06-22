import com.google.gson.Gson;
import com.magenic.jmaqs.webservices.baseWebServiceTest.BaseWebServiceTest;
import com.magenic.jmaqs.webservices.baseWebServiceTest.WebServiceUtils;
import com.magenic.jmaqs.webservices.models.ProductJson;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceTest extends BaseWebServiceTest{

  @Test
  public void getJsonDeserialized()
  {
    String result = "";
    try {
      result = WebServiceUtils.getResponseBody(this.getWebServiceTestObject().getWebServiceWrapper().getContent("/api/XML_JSON/GetProduct/1", ContentType.APPLICATION_JSON, false));
    } catch (Exception e) {
      e.printStackTrace();
    }

    ProductJson productJson = new Gson().fromJson(result, ProductJson.class);
    Assert.assertEquals(productJson.getId(), 1, "Expected to get product 1");



  }
}
