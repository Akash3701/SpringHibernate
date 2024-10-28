let menuicn = document.querySelector(".menuicn");
let nav = document.querySelector(".navcontainer");

menuicn.addEventListener("click", () => {
    nav.classList.toggle("navclose");
});


function logout() {
            if (confirm('You have been logged out.')) {
                window.location.href = 'adminLogin.jsp'; 
            }
        }
		
function showSection(sectionId) {
		    // Hide all content sections
		    const sections = document.querySelectorAll('.content-section');
		    sections.forEach(section => section.style.display = 'none');

		    // Show the selected section
		    document.getElementById(sectionId).style.display = 'block';
		}
		
		
		$(document).ready(function() {
		    $('#viewAllBuyersBtn').on('click', function() {
		        $.ajax({
		            url: '/getBuyersList', // Ensure this path is correct relative to your app's context
		            type: 'GET',
		            dataType: 'json',
		            success: function(buyers) {
		                console.log('Fetched buyers:', buyers); // Debug log
		                const tbody = $('#buyersTable tbody');
		                tbody.empty(); // Clear existing rows

		                if (buyers.length === 0) {
		                    tbody.append('<tr><td colspan="6">No buyers found.</td></tr>');
		                } else {
		                    buyers.forEach(buyer => {
		                        const row = `<tr>
		                            <td>${buyer.buyerId}</td>
		                            <td>${buyer.name}</td>
		                            <td>${buyer.email}</td>
		                            <td>******</td> <!-- Mask password for security reasons -->
		                            <td>${buyer.contactNo}</td>
		                            <td>${buyer.city}</td>
		                        </tr>`;
		                        tbody.append(row);
		                    });
		                }
		            },
		            error: function(xhr, status, error) {
		                console.error('Error fetching buyers:', error);
		                alert('An error occurred while fetching buyers.');
		            }
		        });
		    });
		});
