package paralleltestingusingdataprovider.testclasses;

import org.testng.annotations.Factory;

import paralleltestingusingdataprovider.basetestclass.BaseTest;

public class FactoryAnnotation extends BaseTest{

    @Factory(dataProvider="dataProvidingForParallel")
    public Object[] createTests(String username,String password, String product,String comapnyName,String line1,String line2, String line3, String cityName, String regionName, String postCode, String countryName,String phone) throws Exception {
     return new Object[] {  new TestClassForLoadTesting(username,password,product,comapnyName,line1,line2,line3,cityName,regionName,postCode,countryName,phone)
     };
  
    }
}
