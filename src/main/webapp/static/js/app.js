var resume = {
		
		alert : function(message){
			alert(message);
		},
		
		post : function(path, params) {
			var form = document.createElement("form");
			form.setAttribute("method", 'post');
			form.setAttribute("action", path);
			for ( var key in params) {
				if (params.hasOwnProperty(key)) {
					var value = params[key];
					if (value != undefined) {
						var hiddenField = document.createElement("input");
						hiddenField.setAttribute("type", "hidden");
						hiddenField.setAttribute("name", key);
						hiddenField.setAttribute("value", params[key]);
						form.appendChild(hiddenField);
					}
				}
			}
			document.body.appendChild(form);
			form.submit();
		},
		
		logout : function (csrfToken){
			resume.post('/logout', {
				_csrf : csrfToken
			});
		},
		
		moreProfiles : function() {
			
			var page = parseInt($('#profileContainer').attr('data-profile-number')) + 1;
			var total= parseInt($('#profileContainer').attr('data-profile-total'));
			
			if (page >= total) {
				$('#loadMoreIndicator').remove();
				$('#loadMoreContainer').remove();
				return;
			}
			
			var url = '/fragment/more?page=' + page;
			
			$('#loadMoreContainer').css('display', 'none');
			$('#loadMoreIndicator').css('display', 'block');
			
			$.ajax({
				url : url,
				success : function(data) {
					$('#loadMoreIndicator').css('display', 'none');
					$('#profileContainer').append(data);
					$('#profileContainer').attr('data-profile-number', page);
					if (page >= total-1) {
						$('#loadMoreIndicator').remove();
						$('#loadMoreContainer').remove();
					} else {
						$('#loadMoreContainer').css('display', 'block');
					}
				},
				error : function(data) {
					$('#loadMoreIndicator').css('display', 'none');
					resume.alert('Error! Try again later...');
				}
			});			
		},
		
		initCertificateViewer : function(){
			$('a.certificate-link').click(function(e){
				e.preventDefault();
				var title = $(this).attr('data-title');
				$('#certificateViewer .modal-title').html(title);
				$('#certificateViewer .modal-body img').attr('src', $(this).attr("data-url"));
				$('#certificateViewer').modal({
					show : true
				});
			});
		},
		
		createCertificateUploader : function(csrfToken){
			//https://github.com/kartik-v/bootstrap-fileinput
			$("#certificateFile").fileinput({
				uploadUrl: '/edit/certificates/upload?_csrf='+csrfToken,
			 	allowedFileExtensions : ['jpg', 'png'],
				maxFileCount: 1,
				showPreview:false
			});
			$('#certificateFile').on('fileuploaded', function(event, data, previewId, index) {
			    var response = data.response;
			    if(response.certificateName != null) {
			    	if($('#certificateName').val().trim() == '') {
			    		$('#certificateName').val(response.certificateName);
			    	}
			    }
			    $('#certificateUploader').attr('data-small-url', response.smallUrl);
			    $('#certificateUploader').attr('data-large-url', response.largeUrl);
			});
			$('#certificateFile').on('fileuploaderror', function(event, data, msg) {
			    resume.showErrorDialog(messages.errorUploadCertificate);
			});
		},
		
		certificates : {
			showUploadDialog : function() {
				$('#certificateUploader').modal({
					show : true
				});
			},
			
			add : function (){
				var certificateName = $('#certificateName').val();
				//https://www.tjvantoll.com/2012/08/05/html5-form-validation-showing-all-error-messages/
				if(certificateName.trim() == '') {
					alert('certificateName is null')
					return;
				}
				var template = resume.ui.getTemplate();
				var container = $('#ui-block-container');
				var blockIndex = container.find('.ui-item').length;
				var context = {
					blockIndex : blockIndex,
					name : $('#certificateName').val(),
					smallUrl : $('#certificateUploader').attr('data-small-url'),
					largeUrl : $('#certificateUploader').attr('data-large-url')
				};
				container.append(template(context));
				$('#certificateUploader').modal('hide');
				$('#certificateName').val('');
				$('#certificateFile').fileinput('clear');
			}
		},
		
		createDatePicker : function() {
			/* http://bootstrap-datepicker.readthedocs.org/en/latest/options.html */
			$('.datepicker').datepicker({
				autoclose : true,
				clearBtn : true
			});
		}
}