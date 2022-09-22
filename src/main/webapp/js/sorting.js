function pizzaSorter(obj) {// Query the table
	const table = document.getElementById('listaPizzeTable');
	console.log(table);
	// Query the headers
	const headers = table.querySelectorAll('th');

	// Track sort directions
	const directions = Array.from(headers).map(function(header) {
		return '';
	});

	// Loop over the headers
	[].forEach.call(headers, function(header, index) {
		header.addEventListener('click', function() {
			// This function will sort the column
			sortColumn(index);
		});
	});
	// Query all rows
	const tableBody = table.querySelector('tbody');
	const rows = tableBody.querySelectorAll('tr');

	const sortColumn = function(index) {
		// Get the current direction
		const direction = directions[index] || 'asc';

		// A factor based on the direction
		const multiplier = (direction === 'asc') ? 1 : -1;

		// Clone the rows
		const newRows = Array.from(rows);

		// Sort rows by the content of cells
		newRows.sort(function(rowA, rowB) {
			// Get the content of cells
			const cellA = rowA.querySelectorAll('td')[index].innerHTML;
			const cellB = rowB.querySelectorAll('td')[index].innerHTML;

			switch (true) {
				case cellA > cellB:
					return 1 * multiplier;
				case cellA < cellB:
					return -1 * multiplier;
				case cellA === cellB:
					return 0;
			}
		});

		// Remove old rows
		[].forEach.call(rows, function(row) {
			tableBody.removeChild(row);
		});

		// Append new row
		newRows.forEach(function(newRow) {
			tableBody.appendChild(newRow);
		});

		// Reverse the direction
		directions[index] = direction === 'asc' ? 'desc' : 'asc';
	};

}