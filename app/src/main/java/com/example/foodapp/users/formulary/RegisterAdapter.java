package com.example.foodapp.users.formulary;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foodapp.models.UserFormulary;

import java.util.ArrayList;

public class RegisterAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final UserFormulary userFormulary = new UserFormulary();
    private FormularyViewModel formularyViewModel;

    public RegisterAdapter(@NonNull FragmentManager fm, FormularyViewModel viewModel){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        formularyViewModel = viewModel;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new AgeFragment();
                ((AgeFragment) fragment).SetViewModel(formularyViewModel);
            break;
            case 1:
                fragment = new GenderFragment();
                ((GenderFragment) fragment).SetViewModel(formularyViewModel);
                break;
            case 2:
                fragment = new DietFragment();
                ((DietFragment) fragment).SetViewModel(formularyViewModel);
                break;
            case 3:
                fragment = new WorkoutFragment();
                ((WorkoutFragment) fragment).SetViewModel(formularyViewModel);
                break;
            case 4:
                fragment = new GoalFragment();
                ((GoalFragment) fragment).SetViewModel(formularyViewModel);
                break;
            case 5:
                fragment = new LevelCook();
                ((LevelCook) fragment).SetViewModel(formularyViewModel);
                break;
            case 6:
            fragment= new AlergiasFragment();
            ((AlergiasFragment) fragment).SetViewModel(formularyViewModel);
            break;
            case 7:
                fragment= new FoodFragment();
                ((FoodFragment) fragment).SetViewModel(formularyViewModel);
            break;
            case 8:
                fragment= new EnfermedadesFragment();
                ((EnfermedadesFragment) fragment).SetViewModel(formularyViewModel);
            break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
