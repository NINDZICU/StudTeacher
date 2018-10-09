package ru.kfpu.itis.studteacher.ui.proflie;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.utilities.InjectorUtils;

public class ProfileFragment extends Fragment {

    private Context context;
    private TextView tvName;
    private Button btnExit;
    private ProfileViewModel profileViewModel;

    public static ProfileFragment getInstance(Context context) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setContext(context);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tv_profile_name);
        btnExit = view.findViewById(R.id.btn_profile_exit);

        ProfileViewModelFactory factory = InjectorUtils.provideProfileViewModelFactory(context.getApplicationContext());
        profileViewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
        if (profileViewModel.getUserFromStorage() != null) {
            tvName.setText(profileViewModel.getUserFromStorage().getName());
        }
        btnExit.setOnClickListener(v -> {
            profileViewModel.deleteUserFromStorage();
            System.exit(0);
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

