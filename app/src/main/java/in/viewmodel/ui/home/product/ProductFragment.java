package in.viewmodel.ui.home.product;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.viewmodel.R;
import in.viewmodel.model.cart.addtocart.AddProductToCart;
import in.viewmodel.network.NetworkInterface;
import in.viewmodel.ui.base.BaseFragment;
import in.viewmodel.utils.ConstantUtils;
import in.viewmodel.utils.dialog.ProgressDialog;

public class ProductFragment extends BaseFragment {
    //View Elements
    private View view;

    @BindView(R.id.fProductCategoryTVResponse)
    TextView tvResponseText;

    @BindView(R.id.fProductCategoryAddProductToCart)
    MaterialButton btAddProductsToCart;

    //ViewModel
    private ProductViewModel productViewModel;

    //Objects
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_category, container, false);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.init();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
        setupObservers();
        setupClickListener();
    }

    private void setupObservers() {
        productViewModel.productAddedToCartDataLiveData.observe(getViewLifecycleOwner(), productAddedToCartData -> {
            progressDialog.dismiss();
            tvResponseText.setText(new Gson().toJson(productAddedToCartData));
        });
    }

    private void setupClickListener() {
        btAddProductsToCart.setOnClickListener(view -> {
            addProductsToCart();
        });
    }

    private void initModels() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);

        tvResponseText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void addProductsToCart() {
        progressDialog.show();
        AddProductToCart addProductToCart = new AddProductToCart(
                ConstantUtils.USER_ID,
                ConstantUtils.PRODUCT_QUANTITY_PRICING_ID,
                ConstantUtils.ITEM_QUANTITY_ID,
                ConstantUtils.TOTAL_CART_TOTAL,
                ConstantUtils.USER_UPDATED_BY,
                false
        );
        productViewModel.addProductToCart(addProductToCart);
    }

}
