
package org.forester.archaeopteryx.tools;

import org.forester.archaeopteryx.Constants;
import org.forester.archaeopteryx.MainFrame;
import org.forester.util.ForesterUtil;

public abstract class RunnableProcess implements Runnable {

    long _process_id;

    long getProcessId() {
        return _process_id;
    }

    void setProcessId( final long process_id ) {
        _process_id = process_id;
    }

    void start( final MainFrame mf, final String name ) {
        mf.getMainPanel().getCurrentTreePanel().setWaitCursor();
        setProcessId( mf.getProcessPool().addProcess( name ) );
        mf.updateProcessMenu();
    }

    void end( final MainFrame mf ) {
        final boolean removed = mf.getProcessPool().removeProcess( getProcessId() );
        if ( !removed ) {
            ForesterUtil.printWarningMessage( Constants.PRG_NAME, "could not remove process " + getProcessId()
                    + " from process pool" );
        }
        mf.getMainPanel().getCurrentTreePanel().setArrowCursor();
        mf.updateProcessMenu();
    }
}