package at.teamgotcha.tamagotchi.base;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class ContractV4Fragment<T> extends Fragment {
    private T contract;

    @Override
    public void onAttach(Context context) {
        try {
            contract = (T) context;
        } catch (ClassCastException e) {
            throw new IllegalStateException(context.getClass().getSimpleName()
                    + " does not implement " + getClass().getSimpleName() + "'s contract interface.", e);
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        contract = null;
    }

    public final T getContract() {
        return contract;
    }
}