package in.viewmodel.ui.home.product;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import in.viewmodel.model.cart.addtocart.AddProductToCart;
import in.viewmodel.model.cart.cartadditionresponse.ProductAddedToCartData;
import in.viewmodel.model.cart.cartadditionresponse.ProductAddedToCartResponse;
import in.viewmodel.network.NetworkInterface;
import in.viewmodel.network.RetroFitClient;
import in.viewmodel.ui.base.BaseViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ProductViewModel extends BaseViewModel {

    private ProductRepository networkService;
    private NetworkInterface networkInterface;

    public MutableLiveData<ProductAddedToCartData> productAddedToCartDataLiveData = new MutableLiveData<>();

    public void init() {
        if (networkService != null) {
            return;
        }
        networkInterface = RetroFitClient.getRetrofitInstance().create(NetworkInterface.class);
        networkService = new ProductRepository(networkInterface);
    }

    public void addProductToCart(AddProductToCart addProductToCart) {
        networkService.addProductToCart(addProductToCart, new SingleObserver<ProductAddedToCartResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ProductAddedToCartResponse productAddedToCartResponse) {
                getCartResponse(productAddedToCartResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    private MutableLiveData<ProductAddedToCartData> getCartResponse(ProductAddedToCartResponse productAddedToCartResponse) {
        productAddedToCartDataLiveData.setValue(productAddedToCartResponse.getProductAddedToCartData());
        return productAddedToCartDataLiveData;
    }
}
