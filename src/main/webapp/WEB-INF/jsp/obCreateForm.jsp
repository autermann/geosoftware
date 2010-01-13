<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form name="create" action="createObservation">
	<input type="text" name="title" value="Titel" />
	<select name="category">
		<option value="ar">Argentinien</option>
		<option value="br">Brasilien</option>
		<option value="bg">Bulgarien</option>
		<option value="cl">Chile</option>
		<option value="dk">D&auml;nemark</option>
	</select>
	<input type="text" name="description" value="Beschreibung" />
	<input type="button" value="Abbrechen" />
	<input type="submit" value="OK" />
</form>