
📦 Core Features
✅ User Authentication

Register & Login APIs

Secure JWT token generation

✅ Task Ownership

Each task belongs to the logged-in user

Users can only view their own tasks

✅ Role-Based Authorization

Admin-only APIs protected

Unauthorized access returns 403 Forbidden

✅ Soft Delete (Enterprise Pattern)

Tasks are never physically deleted

Implemented using Hibernate:

@SQLDelete

@Where

✅ Auditing

Automatically tracks:

createdAt

updatedAt

createdBy

updatedBy
