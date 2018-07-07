package com.mrswimmer.shift.presentation.main.fragment.add;

import com.mrswimmer.shift.App;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.domain.interactor.FireService;
import com.mrswimmer.shift.presentation.base.BaseFragment;

import javax.inject.Inject;

import butterknife.OnClick;

public class AddFragment extends BaseFragment {
    @Inject
    FireService fireService;

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_add;
    }

    @OnClick(R.id.add_add)
    void onAddClick() {
        fireService.addNotes();
    }
}
