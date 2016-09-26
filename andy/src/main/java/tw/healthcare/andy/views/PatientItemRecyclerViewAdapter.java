package tw.healthcare.andy.views;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tw.healthcare.andy.R;
import tw.healthcare.andy.databinding.FragmentPatientItemBinding;
import tw.healthcare.andy.models.Patient;

public class PatientItemRecyclerViewAdapter extends RecyclerView.Adapter<PatientItemRecyclerViewAdapter.ViewHolder> {

    private List<Patient> patients;
    private PatientItemViewListener listener;

    public PatientItemRecyclerViewAdapter(List<Patient> patients, PatientItemViewListener listener) {
        this.patients = patients;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentPatientItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.fragment_patient_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bindItem(patients.get(position));
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onPatientItemClick(patients.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentPatientItemBinding binding;

        public ViewHolder(FragmentPatientItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(Patient patient) {
            binding.setPatient(patient);
        }

        public View getView() {
            return binding.getRoot();
        }
    }

    public interface PatientItemViewListener {
        void onPatientItemClick(Patient patient);
    }
}
