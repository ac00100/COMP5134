package org.system;

import java.awt.EventQueue;

import org.system.core.handler.MainSystemHandler;
import org.system.core.view.MainFrameMainSystem;

public class RunApplicationSystem {

	public static void main(String[] args) {
		/**
		 * Launch the application.
		 */
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
		            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
		            }
		        } catch (ClassNotFoundException ex) {
		            java.util.logging.Logger.getLogger(RunApplicationSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (InstantiationException ex) {
		            java.util.logging.Logger.getLogger(RunApplicationSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (IllegalAccessException ex) {
		            java.util.logging.Logger.getLogger(RunApplicationSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		            java.util.logging.Logger.getLogger(RunApplicationSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        }
				try {
					MainSystemHandler systemHandler = new MainSystemHandler(new MainFrameMainSystem());
					systemHandler.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
