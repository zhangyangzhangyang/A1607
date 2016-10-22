package preserter;

import android.util.Log;

import java.util.List;

import model.GiftModel;
import model.IGiftModel;
import view.IGiftView;

/**
 * Created by 张样 on 2016/10/22.
 */
public class GiftPresenter implements IRequestCallback,IGiftPresenter {
    private IGiftModel giftModel = new GiftModel();
    private IGiftView giftView;
    public GiftPresenter(IGiftView giftView) {
        this.giftView = giftView;
    }
    @Override
    public void callback(List<String> datas) {
        if (datas == null || datas.isEmpty()) {
            Log.i("android++", "callback: result is null");
            return;
        }
        this.giftView.refreshAdapter(datas);
    }


    @Override
    public void queryGiftList() {
        giftModel.queryGiftList(this);
    }
}
