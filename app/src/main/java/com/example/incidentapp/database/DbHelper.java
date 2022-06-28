package com.example.incidentapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.incidentapp.models.Officer;
import com.example.incidentapp.models.Person;
import com.example.incidentapp.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String USER_TABLE = "users";
    private static final String ID_COLUMN = "id";
    private static final String INCIDENT_TABLE = "incident";
    private static final String STUDENT_ATTENDANCE_TABLE = "attendance";
    private static final String FIRST_NAME_COLUMN = "firstName";
    private static final String LAST_NAME_COLUMN = "lastName";
    private static final String TOPIC_COLUMN = "topic";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String COMMENT_COLUMN = "comment";
    private static final String CREATED_BY_COLUMN = "createdBy";
    private static final String STATUS_COLUMN = "status";
    private static final String CREATED_AT_COLUMN = "createdAt";
    private static final String UPDATED_AT_COLUMN = "updatedAt";
    private static final String TIME_COLUMN = "currentTime";
    private static final String DATE_COLUMN = "currentDate";
    private static final String SCHEDULE_VENUE_COLUMN = "venue";
    private static final String IS_ONLINE_COLUMN = "isOnline";
    private static final String EMAIL_COLUMN = "email";
    private static final String PASSWORD_COLUMN = "password";
    private static final String CONFIDENCE_LEVEL_COLUMN = "confidenceLevel";
    private static final String SCHEDULE_ID_COLUMN = "scheduleId";
    private static final String COURSE_TABLE = "course";
    private static final String COURSE_CODE_COLUMN = "courseCode";
    private static final String COURSE_NAME_COLUMN = "courseName";
    private static final String OFFICER_TABLE = "officer";
    private static final String SCHEDULE_DAY_COLUMN = "day";
    private static final String SCHEDULE_START_TIME_COLUMN = "startTime";
    private static final String SCHEDULE_END_TIME_COLUMN = "endTime";

    private static final String TAG = "DBHelper";


    public DbHelper(Context context) {
        super(context, USER_TABLE + ".db", null, 1);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "Creating Tables If tables do not exist");

        String createUserTable = "CREATE TABLE " + USER_TABLE + " ( " +
                "" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL_COLUMN + " TEXT ,"
                + PASSWORD_COLUMN + " TEXT," + FIRST_NAME_COLUMN + " TEXT, " + LAST_NAME_COLUMN
                + " TEXT, UNIQUE (" + EMAIL_COLUMN + ") )";

        String createOfficerTable = "CREATE TABLE " + OFFICER_TABLE + " ( " +
                "" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL_COLUMN + " TEXT ,"
                + PASSWORD_COLUMN + " TEXT," + FIRST_NAME_COLUMN + " TEXT, " + LAST_NAME_COLUMN
                + " TEXT, UNIQUE (" + EMAIL_COLUMN + ") )";

        String createIncidentTable = "CREATE TABLE " + INCIDENT_TABLE
                + " ( " + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TOPIC_COLUMN + " TEXT, "
                + DESCRIPTION_COLUMN + " TEXT, "
                + COMMENT_COLUMN + " TEXT, "
                + STATUS_COLUMN + " TEXT, "
                + CREATED_AT_COLUMN + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + UPDATED_AT_COLUMN + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + CREATED_BY_COLUMN + " INT, "
                + "FOREIGN KEY (" + CREATED_BY_COLUMN + ") REFERENCES " + USER_TABLE
                + "(" + ID_COLUMN + ") )";


        sqLiteDatabase.execSQL(createUserTable);
        sqLiteDatabase.execSQL(createOfficerTable);
        sqLiteDatabase.execSQL(createIncidentTable);


        try {


            onAddUsers(sqLiteDatabase, User.getDefaultUsersForSeedingToDatabase());
            onAddOfficers(sqLiteDatabase, Officer.getDefaultUsersForSeedingToDatabase());
//            onAddSchedules(sqLiteDatabase, scheduleEntries);

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }

        Log.i(TAG, "Tables query ran successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int onCreateUser(String email, String firstName, String lastName, String password) {

        Log.i(TAG, "Creating user with email: " + email);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(FIRST_NAME_COLUMN, firstName);
        cv.put(LAST_NAME_COLUMN, lastName);
        cv.put(EMAIL_COLUMN, email);
        cv.put(PASSWORD_COLUMN, password);
//        cv.put(IS_ONLINE_COLUMN, 0);

        long insert = sqLiteDatabase.insert(USER_TABLE, null, cv);

        Log.i(TAG, "Created user with email: " + email);

        sqLiteDatabase.close();
        return (int) insert;
    }

    public void onAddUsers(SQLiteDatabase sqLiteDatabase, List<User> users) {

        Log.i(TAG, "Creating Default Users");


        users.forEach(user -> {

            ContentValues cv = new ContentValues();
            /**
             * TODO
             * Use transactions if possible
             * */

            cv.put(FIRST_NAME_COLUMN, user.getFirstName());
            cv.put(LAST_NAME_COLUMN, user.getLastName());
            cv.put(EMAIL_COLUMN, user.getEmail());
            cv.put(PASSWORD_COLUMN, user.getPassword());

            long insert = sqLiteDatabase.insert(USER_TABLE, null, cv);
            Log.i(TAG, "Created users ");


        });

//        sqLiteDatabase.close();


        Log.i(TAG, "Done creating users");

    }

    public void onAddOfficers(SQLiteDatabase sqLiteDatabase, List<Officer> users) {

        Log.i(TAG, "Creating Default Officers");


        users.forEach(user -> {

            ContentValues cv = new ContentValues();
            /**
             * TODO
             * Use transactions if possible
             * */

            cv.put(FIRST_NAME_COLUMN, user.getFirstName());
            cv.put(LAST_NAME_COLUMN, user.getLastName());
            cv.put(EMAIL_COLUMN, user.getEmail());
            cv.put(PASSWORD_COLUMN, user.getPassword());

            long insert = sqLiteDatabase.insert(OFFICER_TABLE, null, cv);
            Log.i(TAG, "Created officer ");


        });

//        sqLiteDatabase.close();


        Log.i(TAG, "Done creating officers");

    }


    public long onAddUserImage(int id, String userImage) {

        Log.i(TAG, "adding user images");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(ID_COLUMN, id);
        cv.put(TOPIC_COLUMN, userImage);

        long insert = sqLiteDatabase.insert(INCIDENT_TABLE, null, cv);
        Log.i(TAG, "added user images");
        sqLiteDatabase.close();
        return insert;
    }

    public boolean onAddUserToAttendanceRegister(int userId, int scheduleId, double confidenceLevel) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        Log.d(TAG, "On Add User: " + scheduleId);

        Date date = new Date();

        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm a");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-M-yyyy ");

        cv.put(ID_COLUMN, userId);
        cv.put(SCHEDULE_ID_COLUMN, scheduleId);
        cv.put(TIME_COLUMN, timeFormatter.format(date));
        cv.put(DATE_COLUMN, dateFormatter.format(date));
        cv.put(CONFIDENCE_LEVEL_COLUMN, confidenceLevel);

        long insert = sqLiteDatabase.insertWithOnConflict(STUDENT_ATTENDANCE_TABLE, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        Log.i(TAG, "added user to attendance: insertId " + insert);
        sqLiteDatabase.close();

        return insert != -1;
    }

    private Person onGetPerson(String email, String password, String nameOfTable) {

        Log.i(TAG, "getting user by email ");

        String name = "";
        Person user = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();


        Cursor cursor = sqLiteDatabase.query(true, nameOfTable, new String[]{
                FIRST_NAME_COLUMN, LAST_NAME_COLUMN, ID_COLUMN
        }, EMAIL_COLUMN + "=? AND " + PASSWORD_COLUMN + " = ?", new String[]{email, password}, null, null, null, null);

        if (cursor.moveToFirst()) {


            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(FIRST_NAME_COLUMN));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(LAST_NAME_COLUMN));

            if (name.equals(USER_TABLE)) {
                user = new User(id, firstName, lastName, email);
            } else {
                user = new Officer(id, firstName, lastName, email);
            }

        }

        if (!cursor.isClosed()) {
            cursor.close();
        }
        Log.i(TAG, "gotten user");
        sqLiteDatabase.close();

        return user;

    }

    public User onGetUser(String email, String password) {
        Log.i(TAG, "getting user by email ");

        return (User) onGetPerson(email, password, USER_TABLE);
    }

    public Officer onGetOfficer(String email, String password) {
        Log.i(TAG, "getting officer by email ");

        return (Officer) onGetPerson(email, password, OFFICER_TABLE);
    }

    ;


    public String onGetStudentName(int id) {
        Log.i(TAG, "getting user by id: ");

        String name = "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();


        Cursor cursor = sqLiteDatabase.query(true, USER_TABLE, new String[]{
                FIRST_NAME_COLUMN, LAST_NAME_COLUMN
        }, ID_COLUMN + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {


            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(FIRST_NAME_COLUMN));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(LAST_NAME_COLUMN));

            name = String.format("%s %s", firstName, lastName);
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }
        Log.i(TAG, "gotten user");
        sqLiteDatabase.close();

        return name;
    }


    public ArrayList<UserModel> onGetUsers() {
        Log.i(TAG, "getting users");

        ArrayList<UserModel> output = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();


        Cursor cursor = sqLiteDatabase.query(true, USER_TABLE, new String[]{
                ID_COLUMN, EMAIL_COLUMN, FIRST_NAME_COLUMN, LAST_NAME_COLUMN
        }, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL_COLUMN));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(FIRST_NAME_COLUMN));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(LAST_NAME_COLUMN));

                UserModel user = new UserModel(email, firstName, lastName, id);

                output.add(user);

            } while (cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        sqLiteDatabase.close();
        Log.i(TAG, "gotten users");

        return output;
    }


    public ArrayList<ScheduleEntry> onGetSchedule() {
        Log.i(TAG, "getting schedule from database");

        ArrayList<ScheduleEntry> output = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(true, OFFICER_TABLE, new String[]{
                ID_COLUMN, SCHEDULE_DAY_COLUMN, COURSE_CODE_COLUMN, SCHEDULE_START_TIME_COLUMN, SCHEDULE_END_TIME_COLUMN, SCHEDULE_VENUE_COLUMN
        }, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN));
                String day = cursor.getString(cursor.getColumnIndexOrThrow(SCHEDULE_DAY_COLUMN));
                String courseCode = cursor.getString(cursor.getColumnIndexOrThrow(COURSE_CODE_COLUMN));
                String startTime = cursor.getString(cursor.getColumnIndexOrThrow(SCHEDULE_START_TIME_COLUMN));
                String endTime = cursor.getString(cursor.getColumnIndexOrThrow(SCHEDULE_END_TIME_COLUMN));
                String venue = cursor.getString(cursor.getColumnIndexOrThrow(SCHEDULE_VENUE_COLUMN));

                ScheduleEntry entry = new ScheduleEntry(day, courseCode, startTime, endTime, venue).setId(id);

                output.add(entry);

            } while (cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }
        Log.i(TAG, "gotten users");
        sqLiteDatabase.close();

        return output;
    }


    public ArrayList<UserModel> onGetUsersImages() {
        Log.i(TAG, "getting user images");

        ArrayList<UserModel> users;


        users = onGetUsers();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        users.forEach(user -> {

            System.out.println(user.getFirstName());
            Cursor cursor = sqLiteDatabase.query(true, STUDENT_IMG_TABLE, new String[]{
                    TOPIC_COLUMN,
            }, ID_COLUMN + "= ?", new String[]{String.valueOf(user.getId())}, null, null, null, null);

            if (cursor.moveToFirst()) {

                do {

                    String imageLocation = cursor.getString(cursor.getColumnIndexOrThrow(TOPIC_COLUMN));
                    user.addToImages(imageLocation);

                } while (cursor.moveToNext());
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }


            Log.i(TAG, "gotten user images");

        });

        sqLiteDatabase.close();

        return users;
    }

    public ArrayList<AttendanceModel> getAttendanceList(int scheduleId) {

        ArrayList<AttendanceModel> attendanceList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Log.d(TAG, "schedule id; " + scheduleId);

        String query = "SELECT " + STUDENT_ATTENDANCE_TABLE + ".*, " + USER_TABLE + ".* " +
                ", " + OFFICER_TABLE + "." + SCHEDULE_DAY_COLUMN +
                " FROM " + STUDENT_ATTENDANCE_TABLE
                + " INNER JOIN " + USER_TABLE
                + " ON " + USER_TABLE + "." + ID_COLUMN + " = " + STUDENT_ATTENDANCE_TABLE + "." + ID_COLUMN
                + " INNER JOIN " + OFFICER_TABLE
                + " ON " + STUDENT_ATTENDANCE_TABLE + "." + SCHEDULE_ID_COLUMN + " = " + OFFICER_TABLE + "." + ID_COLUMN
                + " WHERE " + STUDENT_ATTENDANCE_TABLE + "." + SCHEDULE_ID_COLUMN + " = ?"
                + " ORDER BY " + TIME_COLUMN + " DESC";

//        String query = "SELECT * FROM student INNER JOIN attendance ON student.id= attendance.id inner join schedule on attendance.scheduleId = schedule.id";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(scheduleId)});
        if (cursor.moveToFirst()) {

            Log.d(TAG, "Returned result " + cursor.getColumnIndexOrThrow(SCHEDULE_ID_COLUMN));

            do {

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(FIRST_NAME_COLUMN));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(LAST_NAME_COLUMN));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL_COLUMN));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(TIME_COLUMN));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE_COLUMN));
                UserModel user = new UserModel(email, firstName, lastName, id);

                attendanceList.add(new AttendanceModel(user, date + " " + time));

            } while (cursor.moveToNext());
        }

        Log.d(TAG, "done");

        if (!cursor.isClosed()) {
            cursor.close();
        }

        sqLiteDatabase.close();

        return attendanceList;
    }

    public void deletePictureFromImageTable(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String query = "DELETE FROM " + STUDENT_IMG_TABLE + " WHERE " + ID_COLUMN + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});

        if (!cursor.isClosed()) {
            cursor.close();
        }

        sqLiteDatabase.close();


    }


    public void deleteUserFromRegistration(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String query = "DELETE FROM " + USER_TABLE + " WHERE " + ID_COLUMN + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});
        if (!cursor.isClosed()) {
            cursor.close();
        }

        sqLiteDatabase.close();

    }


}
