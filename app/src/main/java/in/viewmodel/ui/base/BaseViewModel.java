package in.viewmodel.ui.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

}
