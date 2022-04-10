package com.xiyou.advance.modulespublic.common.mvp;

public interface IPresenter<V extends IView>{
    void attach(V view);

    void detach();
}
