package in.viewmodel.ui.home.product;

import in.viewmodel.model.cart.addtocart.AddProductToCart;
import in.viewmodel.model.cart.cartadditionresponse.ProductAddedToCartResponse;
import in.viewmodel.network.NetworkInterface;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductRepository {
    private NetworkInterface networkInterface;

    public ProductRepository(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    public void addProductToCart(AddProductToCart addProductToCart, SingleObserver<ProductAddedToCartResponse> observer) {
        networkInterface.addProductToCartSingle(addProductToCart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
