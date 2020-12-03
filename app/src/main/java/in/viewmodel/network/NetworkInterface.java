package in.viewmodel.network;


import in.viewmodel.model.cart.addtocart.AddProductToCart;
import in.viewmodel.model.cart.cartadditionresponse.ProductAddedToCartResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkInterface {

    @POST("carts")
    Single<ProductAddedToCartResponse> addProductToCartSingle(@Body AddProductToCart addProductToCart);

}
