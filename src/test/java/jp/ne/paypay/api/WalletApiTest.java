package jp.ne.paypay.api;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.model.BalanceData;
import jp.ne.paypay.model.ProductType;
import jp.ne.paypay.model.ResultInfo;
import jp.ne.paypay.model.WalletBalance;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * API tests for WalletApi
 */

public class WalletApiTest {

    @Mock
    private final WalletApi api = Mockito.spy(new WalletApi());
    private ResultInfo resultInfo;

    @BeforeEach
    public void setUp(){
        ApiClient apiClient = Mockito.mock(ApiClient.class);
        api.setApiClient(apiClient);
        resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
    }

    /**
     * Check user wallet balance
     *
     * Check if user has enough balance to make a payment  **Timeout: 15s**
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void checkWalletBalanceTest() throws ApiException {

        String userAuthorizationId = "user-authorization-id";

        Integer amount = 10;

        String currency = "JPY";

        ProductType productType = null;
        WalletBalance walletBalance = new WalletBalance();
        Assert.assertNotNull(api.getApiClient());
        walletBalance.setResultInfo(resultInfo);
        BalanceData balanceData = new BalanceData();
        balanceData.setHasEnoughBalance(true);
        walletBalance.setData(balanceData);
        ApiResponse<WalletBalance> walletBalanceApiResponse = new ApiResponse<>(00001, null, walletBalance);
        Mockito.when(api.checkWalletBalanceWithHttpInfo(userAuthorizationId, amount, currency, productType)).thenReturn(walletBalanceApiResponse);
        WalletBalance response = api.checkWalletBalance(userAuthorizationId, amount, currency, productType);
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    @Test
    @DisplayName("Wallet Balance API for invalid params test")
    public void checkWalletBalanceInvalidParamsTest() throws ApiException {

        String userAuthorizationId = "user-authorization-id";

        Integer amount = 10;

        String currency = "JPY";

        ProductType productType = null;
        WalletBalance walletBalance = new WalletBalance();
        Assert.assertNotNull(api.getApiClient());
        walletBalance.setResultInfo(resultInfo);

        Assert.assertThrows(ApiException.class, () -> api.checkWalletBalance(null, amount, currency, productType));
        Assert.assertThrows(ApiException.class, () -> api.checkWalletBalance(userAuthorizationId, null, currency, productType));
        Assert.assertThrows(ApiException.class, () -> api.checkWalletBalance(userAuthorizationId, amount, null, productType));
    }

}
