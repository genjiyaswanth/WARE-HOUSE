$(document).ready(function(){
        $("#shipmentModeError").hide();
        $("#shipmentCodeError").hide();
        $("#enableShipmentError").hide();
        $("#shipmentGradeError").hide();
        $("#descriptionError").hide();
        var shipmentModeError=false;
        var shipmentCodeError=false;
        var enableShipmentError=false;
        var shipmentGradeError=false;
        var descriptionError=false;
        function validate_shipmentMode(){
            var shipment_mode= $("#shipmentMode").val();
            if(shipment_mode==''){
                $("#shipmentModeError").show();
                $("#shipmentModeError").html("<b>Please select a Shipment Mode.</b>");
                $("#shipmentModeError").css('color',"red");
                shipmentModeError=false;
            }
            else{
                $("#shipmentModeError").hide();
                shipmentModeError=true;
            }
        }
        function validate_shipmentCode(){
            var shipment_code= $("#shipmentCode").val();
            var exp = /^[A-Z\-\s]{4,12}$/
            if(shipment_code==''){
                $("#shipmentCodeError").show();
                $("#shipmentCodeError").html("<b>Shipment Code cannot be empty.</b>");
                $("#shipmentCodeError").css('color',"red");
                shipmentCodeError=false;
            }
            else if(!exp.test(shipment_code)){
                $("#shipmentCodeError").show();
                $("#shipmentCodeError").html("<b>Shipment Code must be 4–12 uppercase letters. Hyphens and spaces are allowed.</b>");
                $("#shipmentCodeError").css('color',"red");
                shipmentCodeError=false;
            }
            else{
                $.ajax({
                    url: 'validate-shipmentcode',
                    data: {'shipmentCode': shipment_code},
                    success: function(responseText){
                        if(responseText==''){
                            $("#shipmentCodeError").hide();
                            shipmentCodeError=true;
                        }
                        else{
                            $("#shipmentCodeError").show();
                            $("#shipmentCodeError").html(responseText);
                            $("#shipmentCodeError").css('color',"red");
                            shipmentCodeError=false;
                        }
                    }
                });
            }
        }

        function validate_enableShipment(){
            var enableShipment= $("[name='enableShipment']:checked").length;
            if(enableShipment==0){
                $("#enableShipmentError").show();
                $("#enableShipmentError").html("<b>Please choose whether to enable shipment (Yes/No).</b>");
                $("#enableShipmentError").css('color',"red");
                enableShipmentError=false;
            }
            else{
                $("#enableShipmentError").hide();
                enableShipmentError=true;
            }
        }

        function validate_shipmentGrade(){
            var shipmentGrade=$("[name='shipmentGrade']:checked").length;
            if(shipmentGrade==0){
                $("#shipmentGradeError").show();
                $("#shipmentGradeError").html("<b>Please select a Shipment Grade (A, B, or C).</b>");
                $("#shipmentGradeError").css('color',"red");
                shipmentGradeError=false;
            }
            else{
                $("#shipmentGradeError").hide();
                shipmentGradeError=true;
            }
        }

        function validate_description(){
                var description= $("#description").val();
                var exp=/^[A-Za-z0-9\.\,\-\s]{10,100}$/;
                if(description==''){
                    $("#descriptionError").show();
                    $("#descriptionError").html("<b>Description cannot be empty.</b>");
                    $("#descriptionError").css('color',"red");
                    descriptionError=false;
                }
                else if(!exp.test(description)){
                	$("#descriptionError").show();
                    $("#descriptionError").html("<b>Description must be 10–100 characters long and may only contain letters, numbers, spaces, commas, periods, and hyphens.</b>");
                    $("#descriptionError").css('color',"red");
                    descriptionError=false;
					}
                else{
                    $("#descriptionError").hide();
                    descriptionError=true;
                }
            }
       
        $("#shipmentMode").change(function(){
            validate_shipmentMode();
        });
        $("#shipmentCode").keyup(function(){
            $("#shipmentCode").val($("#shipmentCode").val().toUpperCase())
            validate_shipmentCode();
        });

        $("[name='enableShipment']").change(function(){
            validate_enableShipment();
        });

        $("[name='shipmentGrade']").change(function(){
            validate_shipmentGrade();
        });

        $("#description").keyup(function(){
                validate_description();
        });
        

        $("#formsubmit").submit(function(){
				validate_shipmentCode();
                validate_shipmentMode();
                validate_enableShipment();
                validate_shipmentGrade();
                validate_description();
                if(shipmentCodeError && shipmentModeError && enableShipmentError && shipmentGradeError && descriptionError){
                    return true;
                }
                else{
                    return false;
                }
			});
        
       
        
    });