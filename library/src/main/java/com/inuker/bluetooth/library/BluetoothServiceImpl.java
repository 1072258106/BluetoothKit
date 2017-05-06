package com.inuker.bluetooth.library;

import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;

import com.inuker.bluetooth.library.connect.BleConnectManager;
import com.inuker.bluetooth.library.connect.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.search.BluetoothSearchHelper;
import com.inuker.bluetooth.library.search.BluetoothSearchRequest;
import com.inuker.bluetooth.library.search.ISearchResponse;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;
import com.inuker.bluetooth.library.utils.proxy.ProxyUtils;

/**
 * Created by dingjikerbo on 2015/10/29.
 */
public class BluetoothServiceImpl implements IBluetoothService {

    private static IBluetoothService sInstance;

    public static IBluetoothService getInstance() {
        if (sInstance == null) {
            synchronized (BluetoothServiceImpl.class) {
                if (sInstance == null) {
                    sInstance = ProxyUtils.getUIProxy(new BluetoothServiceImpl());
                }
            }
        }
        return sInstance;
    }

    @Override
    public void connect(String mac, BleConnectOptions options, final IResponse response) throws RemoteException {
        BleConnectManager.connect(mac, options, new BleGeneralResponse(response));
    }

    @Override
    public void disconnect(String mac) throws RemoteException {
        BleConnectManager.disconnect(mac);
    }

    @Override
    public void read(String mac, ParcelUuid service, ParcelUuid character, IResponse response) throws RemoteException {
        BleConnectManager.read(mac, service.getUuid(), character.getUuid(), new BleGeneralResponse(response));
    }

    @Override
    public void write(String mac, ParcelUuid service, ParcelUuid character, byte[] value, IResponse response) throws RemoteException {
        BleConnectManager.write(mac, service.getUuid(), character.getUuid(), value, new BleGeneralResponse(response));
    }

    @Override
    public void readDescriptor(String mac, ParcelUuid service, ParcelUuid character, ParcelUuid descriptor, IResponse response) throws RemoteException {
        BleConnectManager.readDescriptor(mac, service.getUuid(), character.getUuid(), descriptor.getUuid(), new BleGeneralResponse(response));
    }

    @Override
    public void writeDescriptor(String mac, ParcelUuid service, ParcelUuid character, ParcelUuid descriptor, byte[] value, IResponse response) throws RemoteException {
        BleConnectManager.writeDescriptor(mac, service.getUuid(), character.getUuid(), descriptor.getUuid(), value, new BleGeneralResponse(response));
    }

    @Override
    public void notify(String mac, ParcelUuid service, ParcelUuid character, IResponse response) throws RemoteException {
        BleConnectManager.notify(mac, service.getUuid(), character.getUuid(), new BleGeneralResponse(response));
    }

    @Override
    public void unnotify(String mac, ParcelUuid service, ParcelUuid character, IResponse response) throws RemoteException {
        BleConnectManager.unnotify(mac, service.getUuid(), character.getUuid(), new BleGeneralResponse(response));
    }

    @Override
    public void indicate(String mac, ParcelUuid service, ParcelUuid character, IResponse response) throws RemoteException {
        BleConnectManager.indicate(mac, service.getUuid(), character.getUuid(), new BleGeneralResponse(response));
    }

    @Override
    public void unindicate(String mac, ParcelUuid service, ParcelUuid character, IResponse response) throws RemoteException {
        BleConnectManager.unnotify(mac, service.getUuid(), character.getUuid(), new BleGeneralResponse(response));
    }

    @Override
    public void readRssi(String mac, IResponse response) throws RemoteException {
        BleConnectManager.readRssi(mac, new BleGeneralResponse(response));
    }

    @Override
    public void search(SearchRequest request, final ISearchResponse response) throws RemoteException {
        BluetoothSearchHelper.getInstance().startSearch(new BluetoothSearchRequest(request),
                new BluetoothSearchResponse(response));
    }

    @Override
    public void stopSearch() throws RemoteException {
        BluetoothSearchHelper.getInstance().stopSearch();
    }

    @Override
    public IBinder asBinder() {
        throw new UnsupportedOperationException();
    }
}
