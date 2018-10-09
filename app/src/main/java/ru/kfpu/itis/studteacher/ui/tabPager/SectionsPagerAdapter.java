package ru.kfpu.itis.studteacher.ui.tabPager;

/**
 * Created by hlopu on 20.05.2018.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.kfpu.itis.studteacher.ui.proflie.ProfileFragment;
import ru.kfpu.itis.studteacher.ui.uploadTask.UploadTaskFragment;
import ru.kfpu.itis.studteacher.ui.home.HomeFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private int COUNT_ITEMS = 3;
    private String tabTitles[] = new String[]{"Список работ", "Добавить", "Профиль"};
    private Context context;
    private NotifyFragment notifyFragment;

    public SectionsPagerAdapter(FragmentManager fm, Context context, NotifyFragment notifyFragment) {
        super(fm);
        this.context = context;
        this.notifyFragment = notifyFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.getInstance(context);
            case 1:
                UploadTaskFragment fragment = UploadTaskFragment.getInstance(context);
                fragment.setNotifyFragment(notifyFragment);
                return fragment;
            case 2:
                return ProfileFragment.getInstance(context);

            default:
                return HomeFragment.getInstance(context);
        }
    }

    @Override
    public int getCount() {
        return COUNT_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE; // есть более высокопроизводительный http://qaru.site/questions/14357/viewpager-pageradapter-not-updating-the-view
    }
}