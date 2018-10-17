package as.bwei.com.tupian.presenter;

import as.bwei.com.tupian.model.ShowModel;
import as.bwei.com.tupian.view.ShowView;

/**
 * Created by HP on 2018/10/15.
 */

public class ShowPresenter {
    private ShowView showView;
    private ShowModel showModel;

    public ShowPresenter(ShowView showView) {
        showModel = new ShowModel();
        this.showView = showView;
    }

    public void show(){
        showModel.show(new ShowModel.ShowCallback() {
            @Override
            public void failure(String msg) {
                showView.failure(msg);
            }

            @Override
            public void success(String msg) {
                showView.success(msg);
            }
        });
    }
}
