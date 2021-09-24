package com.e.uvsafeaustralia.views.functionsFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.e.uvsafeaustralia.databinding.FragmentQuizPageBinding;
import com.e.uvsafeaustralia.db.DBManager;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class QuizPageFragment extends Fragment {
    private FragmentQuizPageBinding binding;
    protected DBManager dbManager;
    public static ArrayList<UserModel> userList;
    private String existingPlayer;
    public static UserModel player;
    private static String nicknameInput;
//    private SharedPreferences sp;
//    private SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbManager = new DBManager(requireActivity());
//        sp = getActivity().getPreferences(Context.MODE_PRIVATE);
//        editor = sp.edit();

        // get list of users from db
        userList = getUsersList();

        // initiate a new player
        // or clear previously saved player
        player = new UserModel();
        binding.nameCallout.setVisibility(View.INVISIBLE);
        binding.textUserName.setVisibility(View.INVISIBLE);
        binding.textAddName.clearComposingText();
        binding.spinnerNamesDropdown.setSelection(0);

        // prepare list of users in dropdown spinner
        ArrayList<String> strUserList = new ArrayList<>();
        if (!userList.isEmpty()) {
            strUserList.add(0, "Select player");
            for (UserModel user : userList)
                strUserList.add(user.getNickName());
        }

        ArrayAdapter existingPlayers = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strUserList);
        if (strUserList.size() != 0) {
            existingPlayers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerNamesDropdown.setAdapter(existingPlayers);
        }

        // Add new user
        binding.editTextInputName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnConfirmAddUser.setEnabled(true);
                nicknameInput = binding.editTextInputName.getText().toString();
            }
        });

        binding.btnConfirmAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editTextInputName.onEditorAction(EditorInfo.IME_ACTION_DONE);
                // check if user exist in list of users
                // if user doesn't exist in db, add as a new user
                UserModel existingUser = getUserDB(nicknameInput);
                if (existingUser.getUserId() == 0) {
                    player = addUser(nicknameInput);
                    userList.add(player);
                    binding.nameCallout.setVisibility(View.VISIBLE);
                    binding.textUserName.setText("Hi "+ player.getNickName());
                    binding.textUserName.setVisibility(View.VISIBLE);
                    binding.buttonStartQuiz.setEnabled(true);
                }
                else
                    Toast.makeText(getActivity(), "Nickname already exist. Select from the options below", Toast.LENGTH_LONG).show();
            }
        });

        // for existing user, show a dropdown options consists of existing users
        binding.spinnerNamesDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select player")) {}
                else {
                    binding.nameCallout.setVisibility(View.INVISIBLE);
                    binding.textUserName.setVisibility(View.INVISIBLE);
                    existingPlayer = String.valueOf(parent.getItemAtPosition(position));
                    binding.btnConfirmSelectUser.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.nameCallout.setVisibility(View.INVISIBLE);
                binding.textUserName.setVisibility(View.INVISIBLE);
                binding.btnConfirmSelectUser.setEnabled(false);
            }
        });

        binding.btnConfirmSelectUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = getUserDB(existingPlayer);
                binding.textUserName.setText("Hi "+ player.getNickName());
                binding.nameCallout.setVisibility(View.VISIBLE);
                binding.textUserName.setVisibility(View.VISIBLE);
                binding.buttonStartQuiz.setEnabled(true);
            }
        });

        binding.buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( requireActivity() , QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private UserModel addUser(String nickname) {
        UserModel thisUser = new UserModel();
        insertUser(nickname);
        thisUser = getUserDB(nickname);
        return thisUser;
    }

    private ArrayList<UserModel> getUsersList() {
        ArrayList<UserModel> users = new ArrayList<>();
        openDbManager();
        Cursor c = dbManager.getAllUsers();
        int userId = 0;
        String nickname = "";
        if (c.moveToFirst()) {
            do {
                userId = c.getInt(0);
                nickname = c.getString(1);
                users.add(new UserModel(userId, nickname));
            } while (c.moveToNext());
        }
        dbManager.close();
        return users;
    }

    private UserModel getUserDB(String newUser) {
        openDbManager();
        Cursor c = dbManager.getUserByNickname(newUser);
        UserModel user = new UserModel();
        int userId = 0;
        String nickname = "";
        if (c.moveToFirst()) {
            do {
                userId = c.getInt(0);
                nickname = c.getString(1);
                user.setUserId(userId);
                user.setNickName(nickname);
            } while (c.moveToNext());
        }
        dbManager.close();
        return user;
    }

    private void insertUser(String user) {
        openDbManager();
        dbManager.insertUser(user);
        dbManager.close();
    }

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}