/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.io.monitor.internal;

import org.eclipse.smarthome.core.events.CommandEvent;
import org.eclipse.smarthome.core.events.CommandEventSubscriber;
import org.eclipse.smarthome.core.events.StateEvent;
import org.eclipse.smarthome.core.events.StateEventSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLogger implements StateEventSubscriber, CommandEventSubscriber {

	static private Logger logger = LoggerFactory.getLogger("runtime.busevents");

	@Override
	public void receiveCommand(CommandEvent event) {
		logger.info("{} received command {}", event.getItemName(), event.getCommand());
	}

	@Override
	public void receiveUpdate(StateEvent event) {
		logger.info("{} state updated to {}", event.getItemName(), event.getState());
	}

}
