<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('/images/d1.avif');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #333;
            padding: 20px;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9); /* Slightly transparent white */
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            margin-top: 30px;
            max-width: 600px;
            position: relative;
        }

        .form-label {
            font-weight: 600;
            color: #333;
        }

        .form-control {
            border-radius: 0.5rem;
            border: 1px solid #ced4da;
            box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.075);
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            border-radius: 0.5rem;
            font-size: 1.1rem;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .alert {
            margin-top: 20px;
        }

        .alert-dismissible .btn-close {
            position: absolute;
            top: 0.5rem;
            right: 0.5rem;
        }

        .go-back-link {
            margin-top: 20px;
            text-align: center;
        }

        .go-back-link a {
            color: #007bff;
            text-decoration: none;
            font-weight: 600;
            font-size: 1.1rem;
        }

        .go-back-link a:hover {
            text-decoration: underline;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            var emailError = "${emailError}";
            var success = "${success}";

            if (emailError) {
                showAlert("danger", emailError);
            } else if (success === "false") {
                showAlert("danger", "Registration failed. Please try again.");
            }

            function showAlert(type, message) {
                var alertHtml = '<div class="alert alert-' + type + ' alert-dismissible fade show" role="alert">' +
                    message +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span>' +
                    '</button>' +
                    '</div>';
                $("#alert-container").html(alertHtml);
                $(".alert").show();
            }
        });
    </script>
</head>
<body>
    <div class="container">
        <h2 class="text-center mb-4">Retailer Registration</h2>
        <div id="alert-container"></div>
        <form action="${pageContext.request.contextPath}/retailer/register" method="post">
            <div class="form-group">
                <label for="business_name" class="form-label">Business Name</label>
                <input type="text" class="form-control" id="business_name" name="business_name" required>
            </div>
            <div class="form-group">
                <label for="contact_no" class="form-label">Contact Number</label>
                <input type="text" class="form-control" id="contact_no" name="contact_no" required>
            </div>
            <div class="form-group">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Register</button>
        </form>
        <div class="go-back-link">
            <a href="${pageContext.request.contextPath}/retailer/login">Go back to login</a>
        </div>
    </div>
</body>
</html>