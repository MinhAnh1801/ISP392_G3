USE [TEST]
GO
/****** Object:  Table [dbo].[Admin_Profile]    Script Date: 9/27/2024 8:49:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin_Profile](
	[admin_id] [int] NOT NULL,
	[full_name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[admin_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Applications]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Applications](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NULL,
	[application_type] [nvarchar](50) NULL,
	[content] [nvarchar](max) NULL,
	[created_at] [datetime] NULL,
	[status] [nvarchar](20) NULL,
	[last_updated] [datetime] NULL,
	[response] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[assignment_submissions]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[assignment_submissions](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[assignment_id] [int] NOT NULL,
	[student_id] [int] NOT NULL,
	[submission_date] [datetime] NULL,
	[submission_content] [text] NULL,
	[grade] [decimal](5, 2) NULL,
	[class_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[assignments]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[assignments](
	[assignment_id] [int] IDENTITY(1,1) NOT NULL,
	[lecturer_id] [int] NOT NULL,
	[subject_id] [int] NOT NULL,
	[assignment_description] [text] NULL,
	[assigned_date] [date] NOT NULL,
	[due_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[assignment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Attendance]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Attendance](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NULL,
	[subject_id] [int] NULL,
	[attendance_date] [date] NULL,
	[status] [nvarchar](10) NULL,
	[reason] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Class]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Class](
	[class_id] [int] IDENTITY(1,1) NOT NULL,
	[class_name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[class_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Classrooms]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Classrooms](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[capacity] [int] NULL,
	[location] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Curriculum]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Curriculum](
	[major_id] [int] NOT NULL,
	[subject_id] [int] NOT NULL,
 CONSTRAINT [PK_Curriculum] PRIMARY KEY CLUSTERED 
(
	[major_id] ASC,
	[subject_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dorm_Residents]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dorm_Residents](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NOT NULL,
	[dorm_room_id] [int] NOT NULL,
	[check_in_date] [date] NULL,
	[check_out_date] [date] NULL,
	[status] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DormRooms]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DormRooms](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[room_number] [nvarchar](10) NOT NULL,
	[capacity] [int] NULL,
	[available_capacity] [int] NULL,
	[building] [nvarchar](50) NULL,
	[room_type] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Exams]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Exams](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subject_id] [int] NULL,
	[exam_date] [date] NULL,
	[start_time] [time](7) NULL,
	[end_time] [time](7) NULL,
	[exam_room] [nvarchar](50) NULL,
	[exam_type] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Grades]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Grades](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NULL,
	[subject_id] [int] NULL,
	[grade] [decimal](5, 2) NULL,
	[upload_date] [datetime] NULL,
	[comments] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GuideDetails]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GuideDetails](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[guideline_id] [int] NULL,
	[step_number] [int] NOT NULL,
	[step_title] [varchar](255) NOT NULL,
	[description] [text] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Guidelines]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Guidelines](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](255) NOT NULL,
	[create_date] [date] NOT NULL,
	[user_id] [int] NULL,
	[category] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lecturer_Profile]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lecturer_Profile](
	[lecturer_id] [int] NOT NULL,
	[expertise] [nvarchar](255) NULL,
	[office] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[lecturer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lecturer_Timetable]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lecturer_Timetable](
	[id] [int] NOT NULL,
	[lecturer_id] [int] NOT NULL,
	[subject_id] [int] NOT NULL,
	[start_time] [time](7) NOT NULL,
	[end_time] [time](7) NOT NULL,
	[day_of_week] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_Lecturer_Timetable] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Major]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Major](
	[id] [int] NOT NULL,
	[major_name] [nchar](10) NULL,
 CONSTRAINT [PK_Major] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Materials]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Materials](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subject_id] [int] NULL,
	[material_name] [nvarchar](255) NULL,
	[material_file] [nvarchar](255) NULL,
	[uploaded_at] [datetime] NULL,
	[uploaded_by] [int] NULL,
	[description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[News]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[News](
	[id] [int] NOT NULL,
	[img] [image] NULL,
	[content] [nvarchar](max) NULL,
 CONSTRAINT [PK_News] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notifications]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notifications](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](200) NULL,
	[content] [nvarchar](max) NULL,
	[role] [nchar](10) NULL,
	[upload_time] [datetime] NULL,
 CONSTRAINT [PK_Notifications] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payments]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[amount] [decimal](18, 2) NULL,
	[payment_date] [datetime] NULL,
	[status] [nvarchar](20) NULL,
	[payment_type] [nvarchar](50) NULL,
	[method] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Profile]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profile](
	[user_id] [int] NOT NULL,
	[full_name] [nvarchar](100) NOT NULL,
	[date_of_birth] [date] NULL,
	[phone_number] [nvarchar](20) NULL,
	[address] [nvarchar](255) NULL,
	[gender] [nvarchar](10) NULL,
	[profile_picture] [nvarchar](255) NULL,
	[bio] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student_Profile]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student_Profile](
	[student_id] [int] NOT NULL,
	[major_id] [int] NOT NULL,
	[year_of_study] [nvarchar](20) NULL,
 CONSTRAINT [PK_Student_Profile] PRIMARY KEY CLUSTERED 
(
	[student_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student_Subjects]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student_Subjects](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NULL,
	[subject_id] [int] NULL,
	[status] [nvarchar](10) NULL,
	[enrollment_date] [datetime] NULL,
	[grade] [decimal](5, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StudentClass]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudentClass](
	[student_id] [int] NOT NULL,
	[class_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[student_id] ASC,
	[class_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subjects]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subjects](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [nvarchar](10) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[credits] [int] NOT NULL,
	[description] [nvarchar](max) NULL,
	[semester] [nvarchar](10) NULL,
	[lecturer_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Timetable]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Timetable](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NULL,
	[subject_id] [int] NULL,
	[day_of_week] [nvarchar](10) NULL,
	[start_time] [time](7) NULL,
	[end_time] [time](7) NULL,
	[classroom_id] [int] NULL,
	[attendance_percentage] [decimal](5, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transactions]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transactions](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[transaction_type] [nvarchar](50) NULL,
	[amount] [decimal](18, 2) NULL,
	[transaction_date] [datetime] NULL,
	[description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 9/27/2024 8:49:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[role] [nvarchar](20) NOT NULL,
	[status] [nvarchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Guidelines] ON 

INSERT [dbo].[Guidelines] ([id], [title], [create_date], [user_id], [category]) VALUES (1, N'hu?ng d?n 1', CAST(N'2024-09-27' AS Date), 1, N'h?c t?p ')
INSERT [dbo].[Guidelines] ([id], [title], [create_date], [user_id], [category]) VALUES (3, N'ádf', CAST(N'2024-09-27' AS Date), 1, N'ág')
SET IDENTITY_INSERT [dbo].[Guidelines] OFF
GO
INSERT [dbo].[Major] ([id], [major_name]) VALUES (1, N'abc       ')
INSERT [dbo].[Major] ([id], [major_name]) VALUES (2, N'bca       ')
GO
INSERT [dbo].[Profile] ([user_id], [full_name], [date_of_birth], [phone_number], [address], [gender], [profile_picture], [bio]) VALUES (1, N'nguyen minh anh', CAST(N'2001-01-01' AS Date), N'0123456789', N'hanoi ', N'female', N'asdfasdf', N'asdfasdf')
GO
INSERT [dbo].[Student_Profile] ([student_id], [major_id], [year_of_study]) VALUES (1, 1, N'02/01/2002')
GO
INSERT [dbo].[Users] ([id], [username], [email], [password], [role], [status]) VALUES (1, N'minhanh ', N'anh@gamil.com', N'123', N'student', N'active')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__DormRoom__FE22F61BB5A8E081]    Script Date: 9/27/2024 8:49:41 PM ******/
ALTER TABLE [dbo].[DormRooms] ADD UNIQUE NONCLUSTERED 
(
	[room_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Subjects__357D4CF90BA0870B]    Script Date: 9/27/2024 8:49:41 PM ******/
ALTER TABLE [dbo].[Subjects] ADD UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__AB6E6164386D361D]    Script Date: 9/27/2024 8:49:41 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__F3DBC5723E2C363B]    Script Date: 9/27/2024 8:49:41 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Applications] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Applications] ADD  DEFAULT (getdate()) FOR [last_updated]
GO
ALTER TABLE [dbo].[Dorm_Residents] ADD  DEFAULT (getdate()) FOR [check_in_date]
GO
ALTER TABLE [dbo].[Grades] ADD  DEFAULT (getdate()) FOR [upload_date]
GO
ALTER TABLE [dbo].[Materials] ADD  DEFAULT (getdate()) FOR [uploaded_at]
GO
ALTER TABLE [dbo].[Payments] ADD  DEFAULT (getdate()) FOR [payment_date]
GO
ALTER TABLE [dbo].[Student_Subjects] ADD  DEFAULT (getdate()) FOR [enrollment_date]
GO
ALTER TABLE [dbo].[Transactions] ADD  DEFAULT (getdate()) FOR [transaction_date]
GO
ALTER TABLE [dbo].[Admin_Profile]  WITH CHECK ADD FOREIGN KEY([admin_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Applications]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[assignment_submissions]  WITH CHECK ADD FOREIGN KEY([assignment_id])
REFERENCES [dbo].[assignments] ([assignment_id])
GO
ALTER TABLE [dbo].[assignment_submissions]  WITH CHECK ADD FOREIGN KEY([class_id])
REFERENCES [dbo].[Class] ([class_id])
GO
ALTER TABLE [dbo].[assignment_submissions]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Student_Profile] ([student_id])
GO
ALTER TABLE [dbo].[assignments]  WITH CHECK ADD FOREIGN KEY([lecturer_id])
REFERENCES [dbo].[Lecturer_Profile] ([lecturer_id])
GO
ALTER TABLE [dbo].[assignments]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Attendance]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Attendance]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Curriculum]  WITH CHECK ADD  CONSTRAINT [FK_Curriculum_Major] FOREIGN KEY([major_id])
REFERENCES [dbo].[Major] ([id])
GO
ALTER TABLE [dbo].[Curriculum] CHECK CONSTRAINT [FK_Curriculum_Major]
GO
ALTER TABLE [dbo].[Curriculum]  WITH CHECK ADD  CONSTRAINT [FK_Curriculum_Subjects] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Curriculum] CHECK CONSTRAINT [FK_Curriculum_Subjects]
GO
ALTER TABLE [dbo].[Dorm_Residents]  WITH CHECK ADD FOREIGN KEY([dorm_room_id])
REFERENCES [dbo].[DormRooms] ([id])
GO
ALTER TABLE [dbo].[Dorm_Residents]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Exams]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Grades]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Profile] ([user_id])
GO
ALTER TABLE [dbo].[Grades]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[GuideDetails]  WITH CHECK ADD FOREIGN KEY([guideline_id])
REFERENCES [dbo].[Guidelines] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Guidelines]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Lecturer_Profile]  WITH CHECK ADD FOREIGN KEY([lecturer_id])
REFERENCES [dbo].[Profile] ([user_id])
GO
ALTER TABLE [dbo].[Lecturer_Timetable]  WITH CHECK ADD FOREIGN KEY([lecturer_id])
REFERENCES [dbo].[Lecturer_Profile] ([lecturer_id])
GO
ALTER TABLE [dbo].[Lecturer_Timetable]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Materials]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Materials]  WITH CHECK ADD FOREIGN KEY([uploaded_by])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Payments]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Profile]  WITH CHECK ADD  CONSTRAINT [FK_Profile_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Profile] CHECK CONSTRAINT [FK_Profile_Users]
GO
ALTER TABLE [dbo].[Student_Profile]  WITH CHECK ADD  CONSTRAINT [FK_Student_Profile_Major] FOREIGN KEY([major_id])
REFERENCES [dbo].[Major] ([id])
GO
ALTER TABLE [dbo].[Student_Profile] CHECK CONSTRAINT [FK_Student_Profile_Major]
GO
ALTER TABLE [dbo].[Student_Profile]  WITH CHECK ADD  CONSTRAINT [FK_Student_Profile_Profile] FOREIGN KEY([student_id])
REFERENCES [dbo].[Profile] ([user_id])
GO
ALTER TABLE [dbo].[Student_Profile] CHECK CONSTRAINT [FK_Student_Profile_Profile]
GO
ALTER TABLE [dbo].[Student_Subjects]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Student_Subjects]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[StudentClass]  WITH CHECK ADD FOREIGN KEY([class_id])
REFERENCES [dbo].[Class] ([class_id])
GO
ALTER TABLE [dbo].[StudentClass]  WITH CHECK ADD  CONSTRAINT [FK_StudentClass_Student_Profile] FOREIGN KEY([student_id])
REFERENCES [dbo].[Student_Profile] ([student_id])
GO
ALTER TABLE [dbo].[StudentClass] CHECK CONSTRAINT [FK_StudentClass_Student_Profile]
GO
ALTER TABLE [dbo].[Subjects]  WITH CHECK ADD FOREIGN KEY([lecturer_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Timetable]  WITH CHECK ADD FOREIGN KEY([classroom_id])
REFERENCES [dbo].[Classrooms] ([id])
GO
ALTER TABLE [dbo].[Timetable]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Timetable]  WITH CHECK ADD FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Transactions]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Applications]  WITH CHECK ADD CHECK  (([status]='Rejected' OR [status]='Approved' OR [status]='Pending'))
GO
ALTER TABLE [dbo].[Attendance]  WITH CHECK ADD CHECK  (([status]='Late' OR [status]='Absent' OR [status]='Present'))
GO
ALTER TABLE [dbo].[Dorm_Residents]  WITH CHECK ADD CHECK  (([status]='Checked-out' OR [status]='Active'))
GO
ALTER TABLE [dbo].[DormRooms]  WITH CHECK ADD CHECK  (([available_capacity]>=(0) AND [available_capacity]<=[capacity]))
GO
ALTER TABLE [dbo].[DormRooms]  WITH CHECK ADD CHECK  (([capacity]>(0)))
GO
ALTER TABLE [dbo].[Exams]  WITH CHECK ADD CHECK  (([exam_type]='Quiz' OR [exam_type]='Final' OR [exam_type]='Midterm'))
GO
ALTER TABLE [dbo].[Payments]  WITH CHECK ADD CHECK  (([payment_type]='Other' OR [payment_type]='Fee' OR [payment_type]='Tuition'))
GO
ALTER TABLE [dbo].[Payments]  WITH CHECK ADD CHECK  (([status]='Failed' OR [status]='Pending' OR [status]='Completed'))
GO
ALTER TABLE [dbo].[Profile]  WITH CHECK ADD CHECK  (([gender]='Other' OR [gender]='Female' OR [gender]='Male'))
GO
ALTER TABLE [dbo].[Student_Subjects]  WITH CHECK ADD CHECK  (([status]='Dropped' OR [status]='Completed' OR [status]='Enrolled'))
GO
ALTER TABLE [dbo].[Timetable]  WITH CHECK ADD CHECK  (([day_of_week]='Saturday' OR [day_of_week]='Friday' OR [day_of_week]='Thursday' OR [day_of_week]='Wednesday' OR [day_of_week]='Tuesday' OR [day_of_week]='Monday'))
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [CK__Users__role__37703C52] CHECK  (([role]='lecturer' OR [role]='student' OR [role]='admin'))
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [CK__Users__role__37703C52]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [CK__Users__status__3864608B] CHECK  (([status]='inactive' OR [status]='active'))
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [CK__Users__status__3864608B]
GO
