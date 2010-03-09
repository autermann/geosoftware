<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Registration Sloth</title></head>
<body>

<center>

<h1>Welcome to Sloth Inc. Registration System</h1>
<br/>

<form method="post" action="login.form">
     <table width="25%" border="1">
          <tr>
               <td align="center" bgcolor="lightblue">Registration</td>
          </tr>
          <tr>
               <td>
                    <table border="0" width="100%">
                         <tr>
                              <td width="33%" align="right">Lastname: </td>
                              <td width="66%" align="left">
                                   <input type="text"
                                          name="familyName"
                                          />
                              </td>

                         </tr>
                         <tr>
                              <td width="33%" align="right">Name: </td>
                              <td width="66%" align="left">
                                   <input type="text" name="name" />
                              </td>
                         </tr>
                                                  <tr>
                              <td width="33%" align="right">E-Mail: </td>
                              <td width="66%" align="left">
                                   <input type="text" name="eMail" />
                              </td>
                         </tr>
                                                  <tr>
                              <td width="33%" align="right">Password: </td>
                              <td width="66%" align="left">
                                   <input type="password" name="password" />
                              </td>
                         </tr>
                         <tr>
                              <td align="center" colspan="2">
                                   <input type="submit" value="Submit">
                              </td>
                         </tr>
                    </table>

               </td>
          </tr>
     </table>

</form>

</center>

</body>
</html>

