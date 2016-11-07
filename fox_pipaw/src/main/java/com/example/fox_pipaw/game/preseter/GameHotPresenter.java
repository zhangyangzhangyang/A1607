package com.example.fox_pipaw.game.preseter;

import com.example.fox_pipaw.Bean.NewAndHotBean;
import com.example.fox_pipaw.Log.LogValue;
import com.example.fox_pipaw.game.model.GameHotModel;
import com.example.fox_pipaw.game.model.IGameModel;
import com.example.fox_pipaw.game.view.IGameView;

import java.util.List;

/**
 * Created by 张样 on 2016/10/27.
 */
public class GameHotPresenter implements IRequestCallback,IGamePresenter {
    private IGameModel gameHotModel = new GameHotModel();
    private IGameView gameView;
    public GameHotPresenter(IGameView gameView) {
        this.gameView = gameView;
    }
    @Override
    public void queryGameList() {
        gameHotModel.queryGiftList(this);

    }

    @Override
    public void callback(List<NewAndHotBean> datas) {
        if (datas == null || datas.isEmpty()){
            LogValue.log("android+++++datas 不能为空",datas.toString());
            return;
        }

        this.gameView.refreshAdapter(datas);

    }
}
