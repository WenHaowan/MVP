package as.bwei.com.show.presenter;

import as.bwei.com.show.model.ShowModel;
import as.bwei.com.show.view.ShowView;

/**
 * Created by HP on 2018/10/15.
 */

public class ShowPresenter {
    private ShowModel showModel;
    private ShowView showView;

    public ShowPresenter(ShowView showView) {
        this.showView = showView;
        showModel = new ShowModel();
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
