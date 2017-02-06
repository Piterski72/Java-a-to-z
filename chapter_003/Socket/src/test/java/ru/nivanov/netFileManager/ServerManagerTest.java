package ru.nivanov.netFileManager;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pit on 03.02.2017.
 */
public class ServerManagerTest {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    @Mock
    private ServerManager.MainDirShow mdr = mock(ServerManager.MainDirShow.class);
    @Mock
    private Settings settings = mock(Settings.class);
    @Mock
    private DataInputStream dataInputStream = mock(DataInputStream.class);
    @Mock
    private DataOutputStream dataOutputStream = mock(DataOutputStream.class);
    @InjectMocks
    private ServerManager underTest;

    /**
     * test for fillactions call method for setting current dir.
     * @throws Exception ..
     */
    @Test
    public void whenFillActionsWorksCorrectlyThenReturnResult() throws Exception {
        underTest = new ServerManager(settings, dataInputStream, dataOutputStream);
        // add settings behavior
        when(settings.getValue("serverDir")).thenReturn("currentDir");
        underTest.fillActions();
        String expected = underTest.getCurrentDir();
        assertThat(expected, is("currentDir"));
    }

    /**
     * test for creating new objects from fillactions method.
     * @throws Exception ..
     */
    @Test
    public void whenFillActionsWorksCorrectly2ThenReturnResult() throws Exception {
        underTest = new ServerManager(settings, dataInputStream, dataOutputStream);
        underTest.fillActions();
        Assert.assertTrue(underTest.getAction(0) instanceof ServerManager.MainDirShow);
        Assert.assertTrue(underTest.getAction(1) instanceof ServerManager.SubDirShow);
        Assert.assertTrue(underTest.getAction(2) instanceof ServerManager.ParentDirShow);
        Assert.assertTrue(underTest.getAction(THREE) instanceof ServerManager.UploadFile);
        Assert.assertTrue(underTest.getAction(FOUR) instanceof ServerManager.DownloadFile);
        Assert.assertTrue(underTest.getAction(FIVE) instanceof ServerManager.ExitProgramm);
    }

    /**
     * test for calling execute method from select method.
     * @throws Exception ..
     */
    @Test
    public void whenSelectWorksCorrectlyThenReturnResult() throws Exception {
        underTest = new ServerManager(settings, dataInputStream, dataOutputStream);
        // add settings behavior
        when(settings.getValue("serverDir")).thenReturn("main dir");
        underTest.fillActions();
        underTest.select(0);
        assertThat(underTest.getCurrentDir(), is("main dir"));
    }

    // @Test
    // public void show() throws Exception {

    // }

}