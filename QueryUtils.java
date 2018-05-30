public class QueryUtils
{
  public static void fetchBookData(String urlString)
  {
    URL url = getURL(queryString);
    
    makeHTTPRequest(url);
  }
}
