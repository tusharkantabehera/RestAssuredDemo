package qalaraOrderDetails;

import java.io.IOException;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.utils.BaseAssertion;
import net.assuresign.utils.JsonUtils;

public class getOrderDetails extends Base {

	String orderID;
	String status;
	
	@Test(enabled = true)
	public void getOrder()
	{
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzcHdjbzJSVUs1cXlnSlRjbHNyeHhQZnJUVi1Rd0FxdnRNQjV3TkZXZXlNIn0.eyJleHAiOjE2MjI3MTE4OTEsImlhdCI6MTYxNDkzNTkwOSwiYXV0aF90aW1lIjoxNjE0OTM1ODkxLCJqdGkiOiI4NTk4YzNlMC04ZTA4LTRkYWUtOTQyMy0yYzY0YzgyZGIwYjgiLCJpc3MiOiJodHRwczovL2lkZW50aXR5LWRldi5xYWxhcmEuY29tL2F1dGgvcmVhbG1zL0dvbGRlbkJpcmREZXYiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMDNmOTZjYTQtNmQzOC00ZmYzLWJkY2ItNzNiNTlmNTYyZjc5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicmVhY3RVSSIsIm5vbmNlIjoiMjk2ODM5NmYtYTExOC00NTZiLTlmYjEtZjVkZGVhNmYyMjA3Iiwic2Vzc2lvbl9zdGF0ZSI6ImUyMTQ2NWI5LTMxMTYtNDRlNC1hMDI0LTI5M2ZhNmEwODBkNSIsImFjciI6IjAiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMzVleW5sNDB6ODUwci5hbXBsaWZ5YXBwLmNvbS8iLCJodHRwczovL2RldmVsb3BtZW50LmQycW9xZXN3b2ljcWVrLmFtcGxpZnlhcHAuY29tIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMzVleW5sNDB6ODUwci5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZ2ItcHJvZC13b3JkcHJlc3MtYWxiLTYzMTU1MTk4Ny5hcC1zb3V0aC0xLmVsYi5hbWF6b25hd3MuY29tIiwiaHR0cHM6Ly9iZXRhLnFhbGFyYS5jb20vKiIsImh0dHBzOi8vYWxwaGEucWFsYXJhLmNvbSIsImh0dHBzOi8vZGV2ZWxvcG1lbnQuZDFpZzRpbTJ6aDY4aTkuYW1wbGlmeWFwcC5jb20vIiwiaHR0cHM6Ly9iZXRhLnFhbGFyYS5jb20qIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMmY5eG41cTB4bnVyeS5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZGV2ZWxvcG1lbnQuZDFmNW4yenFibDNra3EuYW1wbGlmeWFwcC5jb20vIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMjF6dGl0Zm9ndjE3YS5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZDJ4cmR6cjZ1Yzd1ZG4uY2xvdWRmcm9udC5uZXQiLCJodHRwczovL2RldmVsb3BtZW50LmQxaWc0aW0yemg2OGk5LmFtcGxpZnlhcHAuY29tIiwiaHR0cHM6Ly93d3cucHJvZHVjdGFkbWluLWRldi5xYWxhcmEuY29tIiwiaHR0cDovLzEzLjIzMy43OS41MDozMDAwLyIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMCoiLCJodHRwczovL2JldGEucWFsYXJhLmNvbSIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMCIsImh0dHBzOi8vaGltYW5zaHUtZGV2ZWxvcG1lbnQuZDJmOXhuNXEweG51cnkuYW1wbGlmeWFwcC5jb20iLCJodHRwczovL2RldmVsb3BtZW50LmQzNmN3Z2plenY0d2t3LmFtcGxpZnlhcHAuY29tLyIsImh0dHA6Ly8xMy4yMzUuMjM4Ljg3OjMwMDAiLCJodHRwczovL2diLXByb2QtdWktZWxiLTYyNzQ0MTc3My5hcC1zb3V0aC0xLmVsYi5hbWF6b25hd3MuY29tIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMzZjd2dqZXp2NHdrdy5hbXBsaWZ5YXBwLmNvbSIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMC8qIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMWY1bjJ6cWJsM2trcS5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZGV2ZWxvcG1lbnQuZDFxODk2NW85ejZ0NXIuYW1wbGlmeWFwcC5jb20vIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMjB3ZWNxaTRna2JsMy5hbXBsaWZ5YXBwLmNvbS8iXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlBST0RVQ1RfQURNSU5fVUkiLCJPUFNfQURNSU4iLCJCVVlFUl9BRE1JTl9VSSIsIlNFTExFUl9BRE1JTl9VSSIsIm9mZmxpbmVfYWNjZXNzIiwiUVVPVEVfQURNSU5fVUkiLCJ1bWFfYXV0aG9yaXphdGlvbiIsIk9SREVSX0FETUlOX1VJIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzdXBlcmFkbWluIn0.PkUNVgZPvzeik-aYZDtvtAhWXsQ3h5CYZtsNbFv9Qi2WVIV2giRX22rOo_ryKmgeM8RmduaXP6MmXJm6wE8UcrEsBv7ynjikOjWLoWokMAJ73OcIj-bKIxqmx5ytnHNC2_wzb5nAdA-7AoSD5JD7QqrF_5luJMe1ZLGdLdrl-woCZJW9pnioS8MfOtc8U3p8PURVK7s8yuiiy_qeSRx0h02IqPXHyXB-aJdeniGkUccry8MtatuuOdjbxZolPrrJr9fEM4itNhq3xWaQwdttDIxehlLQIpVAnDXZtxEEn7IbTob8Fldg0JEVAPlaTZDBqc95PEX195Zx315OhmuGgw";
		RequestSpecification rs= RestAssured.given().header("Authorization", "Bearer "+ token);;
		rs.contentType(ContentType.JSON);
		Response response = rs.get("https://api-dev.qalara.com:1160/admin/orders/composite/GB10002834");
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		
		String strResponse = response.getBody().asString();
		JsonPath responseObj = new JsonPath(strResponse);

		orderID = responseObj.getString("orderId");
		status = JsonUtils.getKeyValue(response, "status");
		System.out.println(orderID);
		System.out.println(status);
		
		BaseAssertion.verifyStatusCode(response, 200);

	}
	
	@Test(enabled = false)
	public void changeOrder() throws IOException
	{
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzcHdjbzJSVUs1cXlnSlRjbHNyeHhQZnJUVi1Rd0FxdnRNQjV3TkZXZXlNIn0.eyJleHAiOjE2MjI3MTE4OTEsImlhdCI6MTYxNDkzNTkwOSwiYXV0aF90aW1lIjoxNjE0OTM1ODkxLCJqdGkiOiI4NTk4YzNlMC04ZTA4LTRkYWUtOTQyMy0yYzY0YzgyZGIwYjgiLCJpc3MiOiJodHRwczovL2lkZW50aXR5LWRldi5xYWxhcmEuY29tL2F1dGgvcmVhbG1zL0dvbGRlbkJpcmREZXYiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMDNmOTZjYTQtNmQzOC00ZmYzLWJkY2ItNzNiNTlmNTYyZjc5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicmVhY3RVSSIsIm5vbmNlIjoiMjk2ODM5NmYtYTExOC00NTZiLTlmYjEtZjVkZGVhNmYyMjA3Iiwic2Vzc2lvbl9zdGF0ZSI6ImUyMTQ2NWI5LTMxMTYtNDRlNC1hMDI0LTI5M2ZhNmEwODBkNSIsImFjciI6IjAiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMzVleW5sNDB6ODUwci5hbXBsaWZ5YXBwLmNvbS8iLCJodHRwczovL2RldmVsb3BtZW50LmQycW9xZXN3b2ljcWVrLmFtcGxpZnlhcHAuY29tIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMzVleW5sNDB6ODUwci5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZ2ItcHJvZC13b3JkcHJlc3MtYWxiLTYzMTU1MTk4Ny5hcC1zb3V0aC0xLmVsYi5hbWF6b25hd3MuY29tIiwiaHR0cHM6Ly9iZXRhLnFhbGFyYS5jb20vKiIsImh0dHBzOi8vYWxwaGEucWFsYXJhLmNvbSIsImh0dHBzOi8vZGV2ZWxvcG1lbnQuZDFpZzRpbTJ6aDY4aTkuYW1wbGlmeWFwcC5jb20vIiwiaHR0cHM6Ly9iZXRhLnFhbGFyYS5jb20qIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMmY5eG41cTB4bnVyeS5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZGV2ZWxvcG1lbnQuZDFmNW4yenFibDNra3EuYW1wbGlmeWFwcC5jb20vIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMjF6dGl0Zm9ndjE3YS5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZDJ4cmR6cjZ1Yzd1ZG4uY2xvdWRmcm9udC5uZXQiLCJodHRwczovL2RldmVsb3BtZW50LmQxaWc0aW0yemg2OGk5LmFtcGxpZnlhcHAuY29tIiwiaHR0cHM6Ly93d3cucHJvZHVjdGFkbWluLWRldi5xYWxhcmEuY29tIiwiaHR0cDovLzEzLjIzMy43OS41MDozMDAwLyIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMCoiLCJodHRwczovL2JldGEucWFsYXJhLmNvbSIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMCIsImh0dHBzOi8vaGltYW5zaHUtZGV2ZWxvcG1lbnQuZDJmOXhuNXEweG51cnkuYW1wbGlmeWFwcC5jb20iLCJodHRwczovL2RldmVsb3BtZW50LmQzNmN3Z2plenY0d2t3LmFtcGxpZnlhcHAuY29tLyIsImh0dHA6Ly8xMy4yMzUuMjM4Ljg3OjMwMDAiLCJodHRwczovL2diLXByb2QtdWktZWxiLTYyNzQ0MTc3My5hcC1zb3V0aC0xLmVsYi5hbWF6b25hd3MuY29tIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMzZjd2dqZXp2NHdrdy5hbXBsaWZ5YXBwLmNvbSIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMC8qIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMWY1bjJ6cWJsM2trcS5hbXBsaWZ5YXBwLmNvbSIsImh0dHBzOi8vZGV2ZWxvcG1lbnQuZDFxODk2NW85ejZ0NXIuYW1wbGlmeWFwcC5jb20vIiwiaHR0cHM6Ly9kZXZlbG9wbWVudC5kMjB3ZWNxaTRna2JsMy5hbXBsaWZ5YXBwLmNvbS8iXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlBST0RVQ1RfQURNSU5fVUkiLCJPUFNfQURNSU4iLCJCVVlFUl9BRE1JTl9VSSIsIlNFTExFUl9BRE1JTl9VSSIsIm9mZmxpbmVfYWNjZXNzIiwiUVVPVEVfQURNSU5fVUkiLCJ1bWFfYXV0aG9yaXphdGlvbiIsIk9SREVSX0FETUlOX1VJIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzdXBlcmFkbWluIn0.PkUNVgZPvzeik-aYZDtvtAhWXsQ3h5CYZtsNbFv9Qi2WVIV2giRX22rOo_ryKmgeM8RmduaXP6MmXJm6wE8UcrEsBv7ynjikOjWLoWokMAJ73OcIj-bKIxqmx5ytnHNC2_wzb5nAdA-7AoSD5JD7QqrF_5luJMe1ZLGdLdrl-woCZJW9pnioS8MfOtc8U3p8PURVK7s8yuiiy_qeSRx0h02IqPXHyXB-aJdeniGkUccry8MtatuuOdjbxZolPrrJr9fEM4itNhq3xWaQwdttDIxehlLQIpVAnDXZtxEEn7IbTob8Fldg0JEVAPlaTZDBqc95PEX195Zx315OhmuGgw";
		RequestSpecification rs= RestAssured.given().header("Authorization", "Bearer "+ token);;
		rs.contentType(ContentType.JSON);
		rs.body(JsonUtils.payloadGenerator("changeOrderStatus.json"));
		System.out.println(JsonUtils.payloadGenerator("changeOrderStatus.json"));
		Response response = rs.put("https://api-dev.qalara.com:1160/admin/orders/GB10002834");
		System.out.println(response.getStatusCode());
	}
}