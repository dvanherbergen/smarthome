/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.internal.events;

import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.smarthome.core.events.CommandEvent;
import org.eclipse.smarthome.core.events.CommandEventSubscriber;
import org.eclipse.smarthome.core.events.Event;
import org.eclipse.smarthome.core.events.EventBus;
import org.eclipse.smarthome.core.events.StateEvent;
import org.eclipse.smarthome.core.events.StateEventSubscriber;
import org.eclipse.smarthome.core.service.ThreadPoolService;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default SmartHome EventBus implementation. Used for distributing command and
 * state events to all registered subscribers.
 * 
 * @author Davy Vanherbergen
 * 
 */
public class EventBusImpl implements EventBus {

	private static final Logger log = LoggerFactory.getLogger(EventBusImpl.class);

	private ThreadPoolService threadPoolService;

	private CopyOnWriteArrayList<CommandEventSubscriber> commandSubscribers = new CopyOnWriteArrayList<CommandEventSubscriber>();

	private CopyOnWriteArrayList<StateEventSubscriber> stateSubscribers = new CopyOnWriteArrayList<StateEventSubscriber>();

	@Override
	public void postEvent(final Event event) {
		threadPoolService.submit(new Runnable() {
			@Override
			public void run() {
				notifySubscribers(event);
			}
		});
	}

	@Override
	public void sendEvent(Event event) {
		notifySubscribers(event);
	}

	/**
	 * Distribute an Event to all subscribers.
	 * 
	 * @param event Event to distribute.
	 */
	private void notifySubscribers(Event event) {
		if (event instanceof CommandEvent) {
			for (CommandEventSubscriber s : commandSubscribers) {
				try {
					s.receiveCommand((CommandEvent) event);
				} catch (Exception e) {
					// failure to deliver to a single subscriber should not
					// block delivery of events to others.
					log.error("Error delivering event '' to subscriber ''", event.toString(), s.getClass()
							.getSimpleName(), e);
				}
			}
		} else if (event instanceof StateEvent) {
			for (StateEventSubscriber s : stateSubscribers) {
				try {
					s.receiveUpdate((StateEvent) event);
				} catch (Exception e) {
					// failure to deliver to a single subscriber should not
					// block delivery of events to others.
					log.error("Error delivering event '' to subscriber ''", event.toString(), s.getClass()
							.getSimpleName(), e);
				}
			}
		} else {
			log.error("Received unsupported event type '{}'", event.getClass().getSimpleName());
		}
	}

	@Override
	public synchronized void addCommandEventSubscriber(CommandEventSubscriber subscriber) {
		if (!commandSubscribers.contains(subscriber)) {
			commandSubscribers.add(subscriber);
		}
	}

	@Override
	public synchronized void removeCommandEventSubscriber(CommandEventSubscriber subscriber) {
		commandSubscribers.remove(subscriber);
	}

	@Override
	public synchronized void addStateEventSubscriber(StateEventSubscriber subscriber) {
		if (!stateSubscribers.contains(subscriber)) {
			stateSubscribers.add(subscriber);
		}
	}

	@Override
	public synchronized void removeStateEventSubscriber(StateEventSubscriber subscriber) {
		stateSubscribers.remove(subscriber);
	}

	@Override
	public void postUpdate(String itemName, State newState) {
		postEvent(new StateEvent(itemName, newState));
	}

	@Override
	public void postCommand(String itemName, Command cmd) {
		postEvent(new CommandEvent(itemName, cmd));
	}

	@Override
	public void sendCommand(String itemName, Command cmd) {
		sendEvent(new CommandEvent(itemName, cmd));

	}

	/**
	 * Setter for Declarative Services to inject the ThreadPoolService.
	 * 
	 * @param threadPoolService ThreadPoolService instance.
	 */
	public void setThreadPoolService(ThreadPoolService threadPoolService) {
		this.threadPoolService = threadPoolService;
	}

	/**
	 * Setter for Declarative Services to remove the ThreadPoolService.
	 * 
	 * @param threadPoolService ThreadPoolService instance.
	 */
	public void unsetThreadPoolService(ThreadPoolService threadPoolService) {
		this.threadPoolService = null;
	}
}
