package edu.auburn;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class StoreManager {
    IDataAdapter adapter = null;
    private static StoreManager instance = null;

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    private StoreManager(String db) {
        // for future implementation, currently only SQLite is usable
        if (db.equals("Oracle"))
            adapter = new OracleDataAdapter();
        else
        if (db.equals("SQLite"))
            adapter = new SQLiteDataAdapter();
        adapter.connect();
    }

    public IDataAdapter getDataAdapter() {
        return adapter;
    }

    public void setDataAdapter(IDataAdapter a) {
        adapter = a;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Charlie's Store Management System");

        MainView mv = new MainView();
        mv.run();
    }

}
