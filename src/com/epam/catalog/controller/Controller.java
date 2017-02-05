package com.epam.catalog.controller;

import java.util.List;

import com.epam.catalog.controller.command.Command;

public class Controller {

	private final CommandProvider provider = new CommandProvider();
	private final String paramDelimeter = ",";

	public List<?> executeTask(String request) {

		String commandName = "";
		if (request.contains(paramDelimeter)) {
			commandName = request.substring(0, request.indexOf(paramDelimeter));
		} else {
			request = request + ",";
			commandName = request.substring(0, request.indexOf(paramDelimeter));

		}
		Command executionCommand = provider.getCommand(commandName);

		return executionCommand.execute(request);
	}
}
