package com.example.donatelifeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.donatelifeapplication.R;

import java.util.ArrayList;
public class ListAdapter extends ArrayAdapter<User> {
    public ArrayList<User> MainList;
    public ArrayList<User> StudentListTemp;
    public ListAdapter.SubjectDataFilter studentDataFilter;
    public ListAdapter(Context context, int id, ArrayList<User> studentArrayList) {
        super(context, id, studentArrayList);
        this.StudentListTemp = new ArrayList<User>();
        this.StudentListTemp.addAll(studentArrayList);
        this.MainList = new ArrayList<User>();
        this.MainList.addAll(studentArrayList);
    }
    @Override
    public Filter getFilter() {
        if (studentDataFilter == null) {
            studentDataFilter = new ListAdapter.SubjectDataFilter();
        }
        return studentDataFilter;
    }


    public class ViewHolder {
        TextView Name;
        TextView Organ;
        TextView Number;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListAdapter.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout, null);
            holder = new ListAdapter.ViewHolder();
            holder.Name = (TextView) convertView.findViewById(R.id.textviewName);
            holder.Organ = (TextView) convertView.findViewById(R.id.textviewOrgan);
            holder.Number = (TextView) convertView.findViewById(R.id.textviewPhoneNumber);
            convertView.setTag(holder);
        } else {
            holder = (ListAdapter.ViewHolder) convertView.getTag();
        }
        User student = StudentListTemp.get(position);
        holder.Name.setText(student.getName());
        holder.Organ.setText(student.getOrgan());
        holder.Number.setText(student.getNumber());
        return convertView;
    }
    private class SubjectDataFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            charSequence = charSequence.toString().toLowerCase();
            FilterResults filterResults = new FilterResults();
            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<User> arrayList1 = new ArrayList<User>();
                for (int i = 0, l = MainList.size(); i < l; i++) {
                    User subject = MainList.get(i);
                    if (subject.toString().toLowerCase().contains(charSequence))
                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();
                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = MainList;
                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            StudentListTemp = (ArrayList<User>) filterResults.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = StudentListTemp.size(); i < l; i++)
                add(StudentListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}