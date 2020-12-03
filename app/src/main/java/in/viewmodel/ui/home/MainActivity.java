package in.viewmodel.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.viewmodel.R;
import in.viewmodel.ui.base.BaseApp;
import in.viewmodel.ui.home.product.ProductFragment;

import static in.viewmodel.utils.ConstantUtils.ADD_FRAGMENT;

public class MainActivity extends BaseApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        changeFragment(getSupportFragmentManager(),
                R.id.flMain,
                new ProductFragment(),
                ADD_FRAGMENT,
                false,
                null
        );
    }

}