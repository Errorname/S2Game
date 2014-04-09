package bengine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import bengine.Keys.Key;

public class InputHandler implements KeyListener {
	private Map<Integer, Key> mappings = new HashMap<Integer, Key>();

	public InputHandler(Keys keys) {
		mappings.put(KeyEvent.VK_UP, keys.jump);
		mappings.put(KeyEvent.VK_LEFT, keys.left);
		mappings.put(KeyEvent.VK_RIGHT, keys.right);

		mappings.put(KeyEvent.VK_Z, keys.jump);
		mappings.put(KeyEvent.VK_S, keys.left);
		mappings.put(KeyEvent.VK_D, keys.right);

		mappings.put(KeyEvent.VK_SPACE, keys.jump);
		mappings.put(KeyEvent.VK_E, keys.use);
	}

	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	public void keyTyped(KeyEvent ke) {
	}

	private void toggle(KeyEvent ke, boolean state) {
		Key key = mappings.get(ke.getKeyCode());
		if (key != null) {
			key.nextState = state;
		}
	}
}
